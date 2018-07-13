package com.example.module.excel;

import java.io.Serializable;

/**
 * 实体
 */
public class DbDictPo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// alias
	public static final String TABLE_ALIAS = "DbDict";
	

	//columns START
    /**
     * id: id   
     */
	private Integer id;
	
    /**
     * 指标编码,每种字典需前缀（4位）+层级（2位，不足左补0）+3位编码（不足左补零）: dict_code   
     */
	private String dictCode;
	
    /**
     * 父编码，第一层为0: p_code   
     */
	private String pcode;
	
    /**
     * 分组编码,为父编码前缀即可: group_code   
     */
	private String groupCode;
	
    /**
     * 字典名称: dict_name   
     */
	private String dictName;
	
    /**
     * 字典层级: level_num   
     */
	private Integer levelNum;
	
    /**
     * 排序: order_num   
     */
	private Integer orderNum;
	
    /**
     * 层级路径，如1，2，3: path   
     */
	private String path;
	
    /**
     * createUser: create_user   
     */
	private String createUser;
	
    /**
     * updateUser: update_user   
     */
	private String updateUser;
	
    /**
     * 删除标志: is_deleted   
     */
	private Integer isDeleted;
	
	//columns END


	
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer value) {
		this.id = value;
	}
	
	
	public String getDictCode() {
		return this.dictCode;
	}
	
	public void setDictCode(String value) {
		this.dictCode = value;
	}
	
	
	public String getPcode() {
		return this.pcode;
	}
	
	public void setPcode(String value) {
		this.pcode = value;
	}
	
	
	public String getGroupCode() {
		return this.groupCode;
	}
	
	public void setGroupCode(String value) {
		this.groupCode = value;
	}
	
	
	public String getDictName() {
		return this.dictName;
	}
	
	public void setDictName(String value) {
		this.dictName = value;
	}
	
	
	public Integer getLevelNum() {
		return this.levelNum;
	}
	
	public void setLevelNum(Integer value) {
		this.levelNum = value;
	}
	
	
	public Integer getOrderNum() {
		return this.orderNum;
	}
	
	public void setOrderNum(Integer value) {
		this.orderNum = value;
	}
	
	
	public String getPath() {
		return this.path;
	}
	
	public void setPath(String value) {
		this.path = value;
	}
	
	
	public String getCreateUser() {
		return this.createUser;
	}
	
	public void setCreateUser(String value) {
		this.createUser = value;
	}
	
	
	public String getUpdateUser() {
		return this.updateUser;
	}
	
	public void setUpdateUser(String value) {
		this.updateUser = value;
	}
	
	
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	
	public void setIsDeleted(Integer value) {
		this.isDeleted = value;
	}
	

	

}

