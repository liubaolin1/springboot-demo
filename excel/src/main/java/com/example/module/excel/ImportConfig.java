package com.example.module.excel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.util.List;
/**
 * 导入工具类
 *
 * @author liuxianling
 * @date 2018年7月10日
 */
@XStreamAlias("ExcelConfig")
public class ImportConfig {

	@XStreamAsAttribute
	private Integer startRow = 1;
	
	@XStreamAsAttribute
	@XStreamAlias("fields")
	private List<ImportField> fieldList = null;

	public Integer getStartRow() {
		return startRow;
	}

	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}

	public List<ImportField> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<ImportField> fieldList) {
		this.fieldList = fieldList;
	}

}
