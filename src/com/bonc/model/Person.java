/*
 * 文件名：Person.java
 * 版权：Copyright by www.bonc.com.cn
 * 描述：
 * 修改人：xiyan
 * 修改时间：2017年7月28日
 */

package com.bonc.model;

public class Person {
    private String type;
    private String tenantName;
    private String tenantAccount;
    private String seqName;
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTenantName() {
        return tenantName;
    }
    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    public String getTenantAccount() {
        return tenantAccount;
    }
    public void setTenantAccount(String tenantAccount) {
        this.tenantAccount = tenantAccount;
    }
    public String getSeqName() {
        return seqName;
    }
    public void setSeqName(String seqName) {
        this.seqName = seqName;
    }

    
    
}
