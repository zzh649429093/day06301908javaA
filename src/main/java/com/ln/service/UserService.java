package com.ln.service;

import com.github.pagehelper.PageInfo;
import com.ln.entity.*;

import java.util.Date;
import java.util.List;

public interface UserService {

    PageInfo<UserBean> findAll(String username, String sbirthday, String ebirthday, int pageNum, int pageSize);

    List<PowerBean> getPowerList();

    UserBean getUserById(Integer id);

    List<DeptBean> getDeptList();

    List<RoleBean> getRoleListByDeptid(UserBean userBean);

    List<RoleBean> getRoleListByDeptid2(Integer deptid);

    void saveUserDeptRole(Integer id, Integer deptid, Integer rid);

    int deleteuser(Integer id);

    int addUser(User userBean);

    User toupdate(Integer id);

    int updateUser(User user);

    UserBean getLogin(UserBean userBean);

    List<PowerBean> getPowerListByid(Integer id);

    QueryVo jieXiStr1(String str1);

    String jieXiStr2(String str2);

    String getInfo(QueryVo vo);

    String saveData(QueryVo vo, String str2);

    void saveStuQj(Integer id, Double qjtime, Date stime, Date etime, String qjcause);

    void saveStuQj2(ProcessBean pb);

    List<QjVo> getStuQjListBySid(Integer id);

    List<QjVo> getQjshListByUserid(Integer id);

    void saveWdsh(Integer pid, Integer shstatus, Integer id);
}
