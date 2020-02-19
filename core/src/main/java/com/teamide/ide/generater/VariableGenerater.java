package com.teamide.ide.generater;

import java.util.List;

import com.teamide.util.StringUtil;
import com.teamide.variable.Variable;

public class VariableGenerater extends CodeGenerater {

	public StringBuffer generate(int tab, List<Variable> variables) {
		if (variables == null || variables.size() == 0) {
			return content;
		}
		content.append(getTab(tab)).append("// 定义变量").append("\n");
		for (Variable variable : variables) {
			appendVariable(tab, variable);
		}

		return content;
	}

	public void appendVariable(int tab, Variable variable) {
		if (StringUtil.isNotTrimEmpty(variable.getValuer())) {

			content.append(getTab(tab));
			content.append("value = new " + variable.getValuer() + "().getValue();").append("\n");
		} else {
			if (StringUtil.isNotTrimEmpty(variable.getValue())) {
				content.append(getTab(tab));
				content.append("value = JexlTool.invoke(\"" + variable.getValue() + "\", invokeCache);").append("\n");
			} else {
				content.append(getTab(tab));
				content.append("value = JexlTool.invoke(\"" + variable.getName() + "\", invokeCache);").append("\n");

				if (StringUtil.isNotTrimEmpty(variable.getDefaultvalue())) {
					content.append(getTab(tab));
					content.append("if(value == null || StringUtil.isEmptyIfStr(value)) {").append("\n");

					content.append(getTab(tab + 1));
					content.append("value = JexlTool.invoke(\"" + variable.getDefaultvalue() + "\", invokeCache);")
							.append("\n");

					content.append(getTab(tab)).append("}").append("\n");
				}

			}
		}

		content.append(getTab(tab));
		content.append("invokeCache.put(\"" + variable.getName() + "\", value);").append("\n");

	}
}