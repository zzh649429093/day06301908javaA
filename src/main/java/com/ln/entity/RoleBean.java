package com.ln.entity;


public class RoleBean {

    private Integer rid;
    private String rname;

    private DeptBean deptBean = new DeptBean();

    public DeptBean getDeptBean() {
        return deptBean;
    }

    public void setDeptBean(DeptBean deptBean) {
        this.deptBean = deptBean;
    }

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}
