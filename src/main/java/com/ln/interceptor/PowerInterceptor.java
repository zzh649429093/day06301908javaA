package com.ln.interceptor;

import com.ln.entity.UserBean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

public class PowerInterceptor implements HandlerInterceptor {

    private List<String> exceptUrls;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        if(exceptUrls.contains(uri)){
            return true;
        }else{
            UserBean ub = (UserBean) request.getSession().getAttribute("ub");
            if(ub!=null){
                Set<String> urls = (Set<String>) request.getSession().getAttribute("urls");
                if (urls!=null){
                    uri = uri.substring(1);
                    if (urls.contains(uri)){
                        return true;
                    }else {
                        request.setAttribute("msg", "非法访问，越权了！！！！");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                        return false;
                    }
                }
                return false;
            }else{
                request.setAttribute("msg", "请先去登录！！！！");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return false;
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public List<String> getExceptUrls() {
        return exceptUrls;
    }

    public void setExceptUrls(List<String> exceptUrls) {
        this.exceptUrls = exceptUrls;
    }

}
