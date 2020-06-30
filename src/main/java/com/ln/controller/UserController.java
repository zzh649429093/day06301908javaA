package com.ln.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ln.entity.*;
import com.ln.service.RoleService;
import com.ln.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;


    /**
     * 保存我的审核
     */
    @RequestMapping("/saveWdsh")
    public String saveWdsh(HttpServletRequest request,Integer pid,Integer shstatus){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        userService.saveWdsh(pid,shstatus,ub.getId());
        return "redirect:getQjShList.do";
    }




    /**
     * 1号员工是 乐柠主任 22号角色
     * 2号员工是 乐柠院长 21号角色
     *
     *
     * 乐柠讲师  23号角色
     * 乐柠的辅导员 24号角色
     * 乐柠的学生是 25号角色
     */

    /**
     * 我的审核 23 号权限 需要乐柠的讲师、辅导员、主任和院长
     * 还有把23的父亲也赋给  乐柠的这些角色、还有爷爷
     */

    /**
     * 管理登录上来查询我的审核
     */

    @RequestMapping("/getQjShList")
    public String getQjShList(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        /**
         * 先拿着我的id去流程明细表中查询一下，有没有需要我审核的流程，有的话，再去流程表中把流程查出来，
         * 和学生是相反的，学生是先查流程，再查明细，老师是先查明细，再查流程
         * select pid from t_pmx a where a.pstatus =1 and userid=37 其实我就需要pid，查询流程id，因为我的页面展示的时候，
         * 我需要知道这个是谁请假的，请了多次时间，什么时候开始的，那个班级的
         */

        List<QjVo> list = userService.getQjshListByUserid(ub.getId());
        model.addAttribute("list", list);
        return "qjsh_list";
    }

    /**
     * 这个注解虽然不是spring的但是他由Spring来管理啊
     * 400错误，说明类型对不上，日期类型不一样，需要日累类型转换
     * 直接使用实体类来接收，需不需要来转换
     * 因为我么带过来的日期有小时，还有日期需要处理
     * 在控制层处理，也可以在entity里面处理
     */
    @RequestMapping("/saveStuQj2")
    public String saveStuQj2(ProcessBean pb){
        /**
         * 接下来则么处理，开始走我们的业务流程，三个流程，属于业务型的代码，所以我们可以全部在sevice层搞定
         */

        userService.saveStuQj2(pb);
        return "redirect:getStuJtList.do";
    }

    @RequestMapping("/saveStuQj")
    public String saveStuQj(Integer id, Double qjtime, Date stime, Date etime, String qjcause){
        /**
         * 接下来则么处理，开始走我们的业务流程，三个流程，属于业务型的代码，所以我们可以全部在sevice层搞定
         */
        userService.saveStuQj(id,qjtime,stime,etime,qjcause);

        return "";
    }

    /**
     * 转发到学生请假的页面
     */
    @RequestMapping("/toStuQj")
    public String toStuQj(HttpServletRequest request,Model model){
        UserBean ub = (UserBean)request.getSession().getAttribute("ub");
        model.addAttribute("stu", ub);
        return "stu_qj";
    }

    /**
     *
     */
    @RequestMapping("/getStuJtList")
    public String getStuJtList(HttpServletRequest request,Model model){
        /**
         * 需要把这个学生的情况查询到，然后再去查询这个学生请假的情况，我们接着去页面，发送请求
         */
        UserBean ub = (UserBean) request.getSession().getAttribute("ub");
        List<QjVo> list = userService.getStuQjListBySid(ub.getId());
        System.out.println(list);
        model.addAttribute("list", list);
        return "stuqj_list";
    }


    @RequestMapping("/getLogin")
    public String getLogin(UserBean userBean, HttpServletRequest request){
        UserBean ub = userService.getLogin(userBean);
        if (ub!=null){
            request.getSession().setAttribute("ub", ub);
            return "main";
        }
        return "../../index";
    }

    @RequestMapping("/queryAll")
    public String queryAll(Integer id,Model model){
        User user = userService.toupdate(id);
            List<PowerBean> list = roleService.toRolePower(user.getRid());
            String json = JSON.toJSONString(list);
            model.addAttribute("json", json);
            return "user_power";
    }

//    @RequestMapping("/findAll")
//    @ResponseBody
//    public void findAll(String username){
//        List<UserBean> list = userService.findAll(username);
//        System.out.println(list);
//    }

    /**
     * 去给员工分配部门和角色
     * 传过来的参数只有员工id
     * 需要的参数
     * 1、员工的全部信息，需要查询出员工已有的部门和角色（其实只需要部门和角色的id就OK啦，用来回显）
     * 2、全部部门列表
     * 3、角色列表（用户现在所在的部门的所有角色列表）
     */
    @RequestMapping("/toUserDeptRole")
    public String toUserDeptRole(Integer id,Model model){

        //根据id查询员工的信息
       UserBean userBean =  userService.getUserById(id);
       //查询部门的信息
        List<DeptBean> dlist  = userService.getDeptList();
        //3、查询员工所在部门的拥有的全部角色，有可能这个员工目前还没有部门或者没有角色
        List<RoleBean> rlist = userService.getRoleListByDeptid(userBean);

        model.addAttribute("ub", userBean);
        model.addAttribute("dlist", dlist);
        model.addAttribute("rlist", rlist);

        return "user_deptrole";
    }


    @RequestMapping("/getUserList")
    public ModelAndView getUserList(String username, String sbirthday, String ebirthday, @RequestParam(defaultValue = "1") int pageNum, @RequestParam(defaultValue = "3") int pageSize){

        ModelAndView modelAndView = new ModelAndView("user_list");

        PageInfo<UserBean> pageInfo = userService.findAll(username, sbirthday, ebirthday, pageNum, pageSize);

        // 分页查询的列表
        modelAndView.addObject("list", pageInfo.getList());
        // 总页数
        modelAndView.addObject("totalPage", pageInfo.getPages());
        // 总条数
        modelAndView.addObject("count", pageInfo.getTotal());
        // 当前页数
        modelAndView.addObject("currentPage", pageNum);
        if(pageNum-1<0){
            pageNum=1;
        }
        if(pageNum+1>pageInfo.getPages()){
            pageNum=pageInfo.getPages();
        }
        modelAndView.addObject("currentPage", pageNum);
        return modelAndView;
    }

    @RequestMapping("/deleteuser")
    public String deleteuser(Integer id){
      int delete =  userService.deleteuser(id);
         return "redirect:getUserList.do";
    }

    @RequestMapping("/toinsert")
    public String toinsert(){
        return "insert";
    }

//    添加员工
    @RequestMapping("/addUser")
    public String addUser(User userBean){
        System.out.println(userBean.toString());
        int user = userService.addUser(userBean);
        return "redirect:getUserList.do";
    }

    @RequestMapping("/getPowerJson")
    public String getPowerJson(Model model,HttpServletRequest request){
        /**
         * 获取session时候，可以传递Boolean类型的参数，不屑默认是true
         * 要是有就返回原来的，要是没有重新创建
         * 要是设置false，有的话，返回，没有的话，返回null
         */
        UserBean ub = (UserBean) request.getSession(true).getAttribute("ub");
        if (ub!=null){
            List<PowerBean> list = userService.getPowerListByid(ub.getId());

            Set<String> urls = new HashSet<String>();
            for (int x=0; x<list.size(); x++) {
                PowerBean pb = list.get(x);
                if (list.get(x).getUrl()!=null){
                    urls.add(list.get(x).getUrl().trim());
                }
                if ("是".equals(pb.getIsbutton())){
                    /**
                     * list的删除，可以把pb给它，也可以角标给它
                     */
                    list.remove(pb);
                    x--;
                }
                /**
                 * 删除完以后，需要把list的大小减1，要不然下标越界
                 * 比如现在角标是3，把3删掉后，4会自动变成3，但是循环里面已经成为4了，所以就把原来的4越过去了
                 */

            }
            request.getSession().setAttribute("urls", urls);
            String json = JSON.toJSONString(list);
            model.addAttribute("json", json);
        }
        return "left";
    }

    //修改
    @RequestMapping("/toupdate")
    public String toupdate(Model model,Integer id){
        User userBean = userService.toupdate(id);
        model.addAttribute("userBean", userBean);
        return "user_update";
    }

    @RequestMapping("/updateUser")
    public String updateUser(User user){
        System.out.println(user);
       int user1 =  userService.updateUser(user);
        return "redirect:getUserList.do";
    }

    @RequestMapping("/getRlistJosn")
    @ResponseBody
    public List<RoleBean> getRlistJosn(Integer deptid){
        List<RoleBean> rlist = userService.getRoleListByDeptid2(deptid);
        /**
         * ajax回传值，可以只用response打回去，这是servlet的做法。
         * SpringMVC可以支持，直接打回去，需要自己转json，springMVC也可以转json
         */
        System.out.println(rlist.toString());
        return rlist;
    }

    @RequestMapping("/saveUserDeptRole")
    public String saveUserDeptRole(Integer id,Integer deptid,Integer rid){
        userService.saveUserDeptRole(id,deptid,rid);
        return "redirect:getUserList.do";
    }

}
