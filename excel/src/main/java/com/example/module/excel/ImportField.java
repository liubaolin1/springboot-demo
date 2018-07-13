package com.example.module.excel;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
/**
 *
 * @author liuxianling
 * @date 2018年7月10日
 */
@XStreamAlias("field")
public class ImportField {

	@XStreamAsAttribute
	private String name;
	
	/**
	 * 类型  int long string date
	 */
	@XStreamAsAttribute
	private String type;
	
	@XStreamAsAttribute
	private String format;
	
	@XStreamAsAttribute
	private boolean required;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

}
