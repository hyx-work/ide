package com.teamide.ide.processor.repository;

import com.alibaba.fastjson.JSONObject;
import com.teamide.ide.maven.MavenUtil;
import com.teamide.ide.param.RepositoryProcessorParam;

public class RepositoryMaven extends RepositoryBase {

	public RepositoryMaven(RepositoryProcessorParam param) {

		super(param);
	}

	public JSONObject clean(String path) throws Exception {

		this.getLog().info("maven clean,  path:" + path);

		MavenUtil mavenUtil = new MavenUtil(this.param.getMavenHome(path));
		mavenUtil.doClean(this.param.getFile(path), null, this.getLog());
		return null;
	}

	public JSONObject doCompile(String path) throws Exception {

		this.getLog().info("maven compile,  path:" + path);

		MavenUtil mavenUtil = new MavenUtil(this.param.getMavenHome(path));
		mavenUtil.doCompile(this.param.getFile(path), null, this.getLog());

		return null;
	}

	public JSONObject doPackage(String path) throws Exception {

		this.getLog().info("maven package,  path:" + path);

		MavenUtil mavenUtil = new MavenUtil(this.param.getMavenHome(path));
		mavenUtil.doPackage(this.param.getFile(path), null, this.getLog());

		return null;
	}

	public JSONObject install(String path) throws Exception {

		this.getLog().info("maven install,  path:" + path);

		MavenUtil mavenUtil = new MavenUtil(this.param.getMavenHome(path));
		mavenUtil.doInstall(this.param.getFile(path), null, this.getLog());

		return null;
	}

	public JSONObject deploy(String path) throws Exception {

		this.getLog().info("maven deploy,  path:" + path);

		MavenUtil mavenUtil = new MavenUtil(this.param.getMavenHome(path));
		mavenUtil.doDeploy(this.param.getFile(path), null, this.getLog());

		return null;
	}

}
