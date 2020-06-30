package com.ln.service;

import com.ln.entity.DeptBean;
import com.ln.entity.RoleBean;

import java.util.List;

public interface DeptService {

    List<DeptBean> getDeptList();

    DeptBean getDeptByDeptid(Integer deptid);

    List<RoleBean> getRoleList();

    List<Integer> RidsByDeptid(Integer deptid);

    void saveDeptRole(Integer deptid, Integer[] rids);
}
