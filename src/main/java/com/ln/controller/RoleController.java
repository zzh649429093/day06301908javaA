package com.ln.controller;

import com.alibaba.fastjson.JSON;
import com.ln.entity.PowerBean;
import com.ln.entity.RoleBean;
import com.ln.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RoleController {
    @Resource
    private RoleService roleService;


    @RequestMapping("/getRoleList")
    public String getRoleList(Model model){
        List<RoleBean> list = roleService.getRoleList();
        model.addAttribute("list", list);
        return "role_list";
    }

    @RequestMapping("/toRolePower")
    public String toRolePower(Integer rid,Model model){
        List<PowerBean> list = roleService.toRolePower(rid);
        String json = JSON.toJSONString(list);
        model.addAttribute("json", json);
        model.addAttribute("rid",rid);
        return "role_power";
    }

    @RequestMapping("/saveRolePower")
    public String saveRolePower(Integer rid,String ids){

        roleService.saveRolePower(rid,ids);

        return "redirect:getRoleList.do";
    }

}
