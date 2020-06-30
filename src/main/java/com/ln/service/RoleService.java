package com.ln.service;

import com.ln.entity.PowerBean;
import com.ln.entity.RoleBean;

import java.util.List;


public interface RoleService {
    List<RoleBean> getRoleList();

    List<PowerBean> toRolePower(Integer rid);

    void saveRolePower(Integer rid, String ids);
}
