package com.ln.mapper;

import com.ln.entity.DeptBean;
import com.ln.entity.RoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DeptMapper {

   List<DeptBean> getDeptList();

   DeptBean getDeptByDeptid(@Param("deptid") Integer deptid);

   List<RoleBean> getRoleList();

   List<Integer> RidsByDeptid(@Param("deptid") Integer deptid);

   void deleteRoleByDeptid(@Param("deptid") Integer deptid);

   void insertDeptRole(@Param("deptid") Integer deptid, @Param("rid")Integer rid);
}
