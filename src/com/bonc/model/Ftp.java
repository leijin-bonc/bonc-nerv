package com.bonc.model;

public class Ftp {
	
	/*账户*/
	private String accout;
	
	/*租户*/
	private String hold;
	
	/*系统时间*/
	private String sysdata;
	
	/*类型*/
	private String type;
	
	/*目录*/
	private String name;
	
	/*文件夹数*/
	private String files;
	
	/*"文件数(D=E+F+G+H)"*/
	private String dfiles;
	
	/*小于20M文件数*/
	private String files20;
	
	/*"[20M-50M]文件数"*/
	private String files50;
	
	/*"(50M-50M]文件数"*/
	private String files100;
	
	/*大于100M*/
	private String maxfiles;
	
	
	public String getSysdata() {
		return sysdata;
	}
	
	public void setSysdata(String sysdata) {
		this.sysdata = sysdata;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFiles() {
		return files;
	}
	
	
	public void setFiles(String files) {
		this.files = files;
	}
	
	
	public String getDfiles() {
		return dfiles;
	}
	
	
	public void setDfiles(String dfiles) {
		this.dfiles = dfiles;
	}
	
	
	public String getFiles20() {
		return files20;
	}
	
	
	public void setFiles20(String files20) {
		this.files20 = files20;
	}
	
	
	public String getFiles50() {
		return files50;
	}
	
	
	public void setFiles50(String files50) {
		this.files50 = files50;
	}
	
	
	public String getFiles100() {
		return files100;
	}
	
	
	public void setFiles100(String files100) {
		this.files100 = files100;
	}
	
	
	public String getMaxfiles() {
		return maxfiles;
	}
	
	
	public void setMaxfiles(String maxfiles) {
		this.maxfiles = maxfiles;
	}

	public String getAccout() {
		return accout;
	}

	public void setAccout(String accout) {
		this.accout = accout;
	}

	public String getHold() {
		return hold;
	}

	public void setHold(String hold) {
		this.hold = hold;
	}
	
	
}
