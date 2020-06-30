package com.ln.service.impl;

import com.ln.entity.PowerBean;
import com.ln.entity.RoleBean;
import com.ln.mapper.RoleMapper;
import com.ln.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;

    public List<RoleBean> getRoleList() {
        return roleMapper.getRoleList();
    }

    @Override
    public List<PowerBean> toRolePower(Integer rid) {
        /**
         * 1、去吧权限list全部查出来
         * 2、需要把该角色原来的权限全部查出来
         *  3、回显
         */
        List<PowerBean> list = roleMapper.getPowerList();
        List<Integer> ids = roleMapper.getIdsByRid(rid);
        if (ids!=null&&ids.size()>=1){
            if (list!=null&&list.size()>=1){
                for (Integer id : ids) {
                    for (PowerBean pb : list) {
                        if (id.equals(pb.getId())){
                            pb.setChecked(true);
                            break;
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public void saveRolePower(Integer rid, String ids) {
        /**
         * 先删除后新增
         */
        roleMapper.deleteRolePowerByRid(rid);
        if(ids!=null&&ids.length()>=1){
            String[] sids = ids.split(",");
            /**
             * 可以强转成int，可以不转，mysql可以容错，就是可以把一个string类的int直接插进int类型里面
             */
            for (String sid : sids) {
                roleMapper.insertRolePower(rid,sid);
            }
        }
    }
}
