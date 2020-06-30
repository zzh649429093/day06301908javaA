package com.ln.mapper;

import com.ln.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<UserBean> findAll(@Param("username") String username, @Param("sbirthday") String sbirthday, @Param("ebirthday") String ebirthday);

    List<PowerBean> getPowerList();

    UserBean getUserById(@Param("id") Integer id);

    List<DeptBean> getDeptList();

    List getRoleListByDeptid(@Param("deptid") Integer deptid);

    void saveUserDeptRole(@Param("id") Integer id,@Param("deptid") Integer deptid,@Param("rid") Integer rid);

    void deleteuser(@Param("id") Integer id);

    int addUser(User userBean);

    User toupdate(@Param("id") Integer id);

    int updateUser(User user);

    List<UserBean> getLogin(@Param("username") String username);

    List<PowerBean> getPowerListByid(@Param("id") Integer id);

    RsBean getRsSmoke(String cardno);

    RsBean getRsWine(String cardno);

    void saveSmoke(SmokeBean smokeBean);

    void saveWine(WineBean wineBean);

    void insertPorcess(ProcessBean pb);

    GradeBean getGradeByGid(@Param("gid") Integer gid);

    void insertPorcessPmx(PmxBean pmxBean);

    List<QjVo> getStuQjListBySid(@Param("sid") Integer sid);

    Integer getUserIdByPid(@Param("pid") Integer pid);

    Integer getUserIdByPidMaxShunxu(@Param("pid") Integer pid);

    Integer getUserIdByPidNopass(@Param("pid") Integer pid);

    QjVo getUnameAndRnameById(@Param("id") Integer id);

    List<Integer> getPidsByUserid(@Param("userid") Integer userid);

    QjVo getProcessById(@Param("id") Integer id);

    QjVo getStuInfoBySid(@Param("id") Integer id);

    void updateProcessStatus(@Param("pid") Integer pid,@Param("shstatus") Integer shstatus);

    void updatePmxStatus(@Param("pid") Integer pid, @Param("userid") Integer userid,@Param("shstatus")Integer shstatus);

    Integer getMaxPshunxu(@Param("pid") Integer pid);

    Integer getQjMxInfo(@Param("pid") Integer pid, @Param("userid") Integer userid);

    void updatePmxShunxu(@Param("pid") Integer pid, @Param("pshunxu") Integer pshunxu);
}
