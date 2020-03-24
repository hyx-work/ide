package com.teamide.ide.deployer.install.java;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.teamide.ide.constant.IDEConstant;
import com.teamide.ide.deployer.DeployParam;
import com.teamide.ide.shell.Shell;
import com.teamide.ide.shell.java.JavaShell;

public class JavaInternalStarterProcess extends JavaStarterProcess {

	public JavaInternalStarterProcess(DeployParam param) {
		super(param);

	}

	@Override
	public Shell getShell() {
		JavaShell shell = new JavaShell(param.starter.starterFolder);
		return shell;
	}

	@Override
	public String getStartShell() throws Exception {

		param.starter.getLog().info("tomcat root " + param.starter.starterServerFolder.toURI().getPath());

		File appFolder = getAppFolder(param.starter.starterServerFolder, null);

		String hostname = param.option.getHostname();
		String port = "" + param.option.getPort();
		String app = appFolder.getAbsolutePath();
		String contextpath = param.option.getContextpath();

		JavaShell shell = (JavaShell) this.shell;
		shell.setJava_home(getJavaHome());
		shell.setJava_envp(getJavaEnvp());

		List<File> lib_folders = new ArrayList<File>();
		lib_folders.add(new File(param.starter.starterServerFolder, "lib"));
		shell.setLib_folders(lib_folders);

		Map<String, String> envps = new HashMap<String, String>();
		envps.put("SERVER_ROOT", param.starter.starterServerFolder.getAbsolutePath());
		envps.put("SERVER_PORT", port);
		envps.put("SERVER_HOSTNAME", hostname);
		envps.put("SERVER_CONTEXTPATH", contextpath);
		envps.put("SERVER_APP", app);
		shell.setEnvps(envps);

		shell.setMain("com.teamide.server.ServerMain");

		return shell.getShellString();
	}

	@Override
	public String getStopShell() throws Exception {
		return null;
	}

	@Override
	public File getServer() throws Exception {

		File tomcat_folder = new File(IDEConstant.PLUGINS_SERVER_FOLDER, param.option.getInternaltomcat());
		if (!tomcat_folder.exists()) {
			throw new Exception(param.option.getInternaltomcat() + " is not defind");
		}
		return tomcat_folder;
	}

	@Override
	public File getPIDFile() throws Exception {
		return shell.getPIDFile();
	}

	@Override
	public void copyProject() throws Exception {

		outWebapps(param.starter.starterServerFolder);
	}
}
