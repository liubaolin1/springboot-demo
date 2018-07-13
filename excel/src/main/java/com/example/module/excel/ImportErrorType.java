package com.example.module.excel;

/**
 * 导入错误类型
 *
 * @author liuxianling
 * @date 2018年7月10日
 */
public enum ImportErrorType {

	REQUIRED("【%s】第%d行数据，不可为空！"),

	FORMAT_ERROR("【%s】第%d行数据，数据格式错误！"),

	TYPE_ERROR("【%s】第%d行数据，数据数据类型错误！");

	private String desc;

	private ImportErrorType(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}
