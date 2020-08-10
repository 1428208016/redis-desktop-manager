package com.lingzhen.myproject.oldproject.pojo;

import java.util.List;

public class zTreeModel {

	private int id;
	private int pId;
	private String name;
	private List<zTreeModel> children;
	private Boolean checked = false;
	private String url;
	private String Target;
	private Boolean isParent = false;
	
	private String pkId;
	private String nocheck;
	public String getNocheck() {
		return nocheck;
	}
	public void setNocheck(String nocheck) {
		this.nocheck = nocheck;
	}
	public String getOpen() {
		return open;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	private String open;
	
	 
	 
	public String getPkId() {
		return pkId;
	}
	public void setPkId(String pkId) {
		this.pkId = pkId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<zTreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<zTreeModel> children) {
		this.children = children;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getTarget() {
		return Target;
	}
	public void setTarget(String target) {
		Target = target;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getIsParent() {
		return isParent;
	}
	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}
	
}
