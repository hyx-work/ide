package com.teamide.ide.plugin;

import com.alibaba.fastjson.JSONObject;
import com.teamide.ide.param.ProjectProcessorParam;

public class PluginParam {

	protected final JSONObject option;

	protected final ProjectProcessorParam param;

	public PluginParam(ProjectProcessorParam param, JSONObject option) {
		this.param = param;
		this.option = option;
	}

	public JSONObject getOption() {
		return option;
	}

	public ProjectProcessorParam getParam() {
		return param;
	}

}
