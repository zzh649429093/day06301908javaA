package com.ln.service.impl;

import com.ln.entity.DeptBean;
import com.ln.entity.RoleBean;
import com.ln.mapper.DeptMapper;
import com.ln.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<DeptBean> getDeptList() {
        return deptMapper.getDeptList();
    }

    public DeptBean getDeptByDeptid(Integer deptid) {
        return deptMapper.getDeptByDeptid(deptid);
    }

    public List<RoleBean> getRoleList() {
        /**
         * 是去查询角色列表的，可以把角色的mapper注入进来，直接查询
         * 自己查询也行，但是角色的里面可定会用这个
         */
        return deptMapper.getRoleList();
    }

    public List<Integer> RidsByDeptid(Integer deptid) {

        return deptMapper.RidsByDeptid(deptid);
    }

    public void saveDeptRole(Integer deptid, Integer[] rids) {
        /**
         * 把这个部门原来的rid全部清空，其实就是去Role里面把deptid=穿过来的这个deptid的删掉
         */
        if(deptid!=null){
            deptMapper.deleteRoleByDeptid(deptid);
            if(rids!=null&&rids.length>=1){
                for (Integer rid : rids) {
                    deptMapper.insertDeptRole(deptid,rid);
                }
            }
        }
    }
}
