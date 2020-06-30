package com.ln.controller;

import com.ln.entity.InfoBean;
import com.ln.entity.QueryVo;
import com.ln.entity.UserBean;
import com.ln.service.UserService;
import com.ln.utils.JieXiXml;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestDataController {

    @Resource
    private UserService userService;

    /**
     * 8、post的对象参数
     */
    @RequestMapping("/testPost5")
    public InfoBean testPost5(@RequestBody InfoBean infoBean,String meaning,Integer flag){
        infoBean.setUname(infoBean.getUname()+"我看到你了");
        System.out.println(meaning+flag);
        return infoBean;
    }

    /**
     * 7、post的对象参数
     */
    @RequestMapping("/testPost4")
    public InfoBean testPost4(@RequestBody InfoBean infoBean){
        infoBean.setUname(infoBean.getUname()+"我看到你了");
        return infoBean;
    }

    /**
     * 6、post的对象参数
     */
    @RequestMapping("/testPost3")
    public String testPost3(@RequestBody InfoBean infoBean){
        return infoBean.toString();
    }

    /**
     * 5、get有参
     */
    @RequestMapping(value = "/testPost2",method = RequestMethod.POST)
    public String testPost2(String name,Integer age){
        return name+"你终于来了，你的age是"+age+"岁了";
    }

    /**
     * 4、无参post请求
     * @return
     */
    @RequestMapping("/testPost1")
    public String testPost1(){
        System.out.println(123);
        return "123";
    }

    /**
      * 3、get有参 url接受参数
     */
    @RequestMapping("/testGet3")
    public String testGet3(String name,Integer age){
        return name+"你终于来了，你的age是"+age+"岁了";
    }


    /**
     * 2、get有参
     */
    @RequestMapping("/testGet2")
    public String testGet2(String name,Integer age){
        return name+"你终于来了，你的age是"+age+"岁了";
    }

    /**
     * 无参get请求
     * @return
     */
    @RequestMapping("/testGet1")
    public void testGet1(){
        System.out.println(123);
    }

    /**
     *
     * @param str1
     * @param str2
     * @return
     * 2、第二个接口，接受分厂发来的数据
     */
    @RequestMapping("reciDataInterface")
    public String reciDataInterface(String str1,String str2){
       // str1 = "<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
       // str2 = "<CONTENT><CARDNO>xy00001</CARDNO><MADEDATE>2018-06-02 11:37:17</MADEDATE><ADDRESS>北京</ADDRESS><PRICE>35</PRICE><NAME>黄鹤楼</NAME></CONTENT>";
        /**
         * 接受到分厂发过来的数据后，我们先解析第一个参数
         */
        QueryVo vo = JieXiXml.jieXiStr1(str1);
        /**
         * 判断vo是否为空，为空解析失败
         */
        if (vo==null){
            return "<MEG><CODE>0</CODE><CONTENT>参数一解析失败</CONTENT></MEG>";
        }else {
            /**
             * 解析成功，判断登录，鉴权（判断有没有资格）
             */
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);
            /**
             * 登录成功和失败
             */
            if (ru==null){
                return "<MEG><CODE>0</CODE><CONTENT>用户名或者密码错误</CONTENT></MEG>";
            }else {
                /**
                 *登录成功，根据参数一解析出来code解析参数二
                 * code不一样，参数二不一样
                 */
                return userService.saveData(vo,str2);
            }
        }
    }


    @RequestMapping("/getDataInterface")
    public String getDataInterface(String str1,String str2){
        /**
         * 这两个参数是对方传递过来的，模拟真实，在开发总部的时候，不要去考虑分布
         */
        //str1 = "<MEG><UNAME>admin</UNAME><PWD>admin</PWD><CODE>01</CODE></MEG>";
        //str2 = "<CONTENT><CARDNO>xy0003</CARDNO></CONTENT>";

        QueryVo vo = userService.jieXiStr1(str1);
        if (vo==null){
            /**
             * 解析失败
             */
            return "<result><MEG><CODE>0</CODE></MEG></result>";
        }else {
            /***
             * 参数1解析成功
             */
            UserBean userBean = new UserBean();
            userBean.setUsername(vo.getUname());
            userBean.setPassword(vo.getPwd());
            UserBean ru = userService.getLogin(userBean);

            if (ru==null){
                return "<result><MEG><CODE>1</CODE></MEG></result>";
            }else {
               String cardno = userService.jieXiStr2(str2);
               if (cardno==null){
                   return "<result><MEG><CODE>0</CODE></MEG></result>";
               }else {
                    vo.setCardno(cardno);

                    String rs = userService.getInfo(vo);
                    if (rs==null){
                        return "<result><MEG><CODE>2</CODE></MEG></result>";
                    }else {
                        System.out.println("<result><MEG><CODE>3</CODE></MEG>"+rs+"</result>");
                        return "<result><MEG><CODE>3</CODE></MEG>"+rs+"</result>";
                    }
               }
            }
        }
    }

}
