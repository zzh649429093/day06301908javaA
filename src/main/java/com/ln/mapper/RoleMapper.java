package com.ln.mapper;

import com.ln.entity.PowerBean;
import com.ln.entity.RoleBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<RoleBean> getRoleList();

    List<Integer> getIdsByRid(@Param("rid") Integer rid);

    List<PowerBean> getPowerList();

    void deleteRolePowerByRid(@Param("rid") Integer rid);

    void insertRolePower(@Param("rid") Integer rid, @Param("sid") String sid);
}
