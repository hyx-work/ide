package com.teamide.ide.processor.repository;

import com.alibaba.fastjson.JSONObject;
import com.teamide.ide.deployer.Deploy;
import com.teamide.ide.handler.DeployHandler;
import com.teamide.ide.param.RepositoryProcessorParam;

public class RepositoryStarter extends RepositoryBase {

	public RepositoryStarter(RepositoryProcessorParam param) {

		super(param);
	}

	public JSONObject deploy(String projectPath, JSONObject option) throws Exception {

		this.getLog().info("starter deploy,  projectPath:" + projectPath);

		String token = DeployHandler.deploy(this.param, projectPath, option.getString("name"));
		JSONObject result = new JSONObject();
		result.put("token", token);
		return result;
	}

	public JSONObject deploy(String token) throws Exception {

		this.getLog().info("starter deploy,  token:" + token);

		DeployHandler.deploy(token);
		JSONObject result = new JSONObject();
		return result;
	}

	public JSONObject stop(String token) throws Exception {

		this.getLog().info("starter stop,  token:" + token);
		Deploy deploy = DeployHandler.get(token);

		if (deploy != null) {
			deploy.stop();
		}
		JSONObject result = new JSONObject();
		return result;
	}

	public JSONObject start(String token) throws Exception {

		this.getLog().info("starter start,  token:" + token);

		Deploy deploy = DeployHandler.get(token);

		if (deploy != null) {
			new Thread() {

				@Override
				public void run() {
					try {
						deploy.start();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}.start();
		}
		JSONObject result = new JSONObject();
		return result;
	}

	public JSONObject remove(String token) throws Exception {

		this.getLog().info("starter remove,  token:" + token);

		new Thread() {

			@Override
			public void run() {
				try {
					DeployHandler.remove(token);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}.start();

		JSONObject result = new JSONObject();
		return result;
	}

	public JSONObject logClean(String token) throws Exception {

		this.getLog().info("starter log clean,  token:" + token);
		Deploy deploy = DeployHandler.get(token);

		if (deploy != null) {
			deploy.cleanLog();

		}
		JSONObject result = new JSONObject();
		return result;
	}

	public JSONObject status(String token) throws Exception {
		Deploy deploy = DeployHandler.get(token);

		if (deploy != null) {
			return deploy.getStatus();
		} else {
			JSONObject json = new JSONObject();
			json.put("token", token);
			json.put("removed", true);
			return json;
		}
	}

}
