package com.ln.controller;

import com.ln.entity.DeptBean;
import com.ln.entity.RoleBean;
import com.ln.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class DeptController {

    @Autowired
    private DeptService deptService;

    @RequestMapping("/getDeptList")
    public String getDeptList(Model model){
        List<DeptBean> deptList = deptService.getDeptList();
        model.addAttribute("list", deptList);
        return  "dept_list";
    }

    @RequestMapping("/saveDeptRole")
    public String saveDeptRole(Integer deptid,Integer[] rids){
        deptService.saveDeptRole(deptid,rids);
        return "redirect:getDeptList.do";
    }

    @RequestMapping("/toDeptRole")
    public String toDeptRole(Integer deptid,Model model){

       DeptBean db = deptService.getDeptByDeptid(deptid);
       List<RoleBean> list = deptService.getRoleList();
       List<Integer> rids = deptService.RidsByDeptid(deptid);
        model.addAttribute("db", db);
        model.addAttribute("list", list);
        model.addAttribute("rlist", rids);
       return "dept_role";

    }

}
