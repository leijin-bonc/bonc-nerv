package com.bonc.model;

public class Ftp {
	
	/*�˻�*/
	private String accout;
	
	/*�⻧*/
	private String hold;
	
	/*ϵͳʱ��*/
	private String sysdata;
	
	/*����*/
	private String type;
	
	/*Ŀ¼*/
	private String name;
	
	/*�ļ�����*/
	private String files;
	
	/*"�ļ���(D=E+F+G+H)"*/
	private String dfiles;
	
	/*С��20M�ļ���*/
	private String files20;
	
	/*"[20M-50M]�ļ���"*/
	private String files50;
	
	/*"(50M-50M]�ļ���"*/
	private String files100;
	
	/*����100M*/
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
