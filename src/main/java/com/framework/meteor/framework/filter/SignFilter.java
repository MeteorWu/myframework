package com.framework.meteor.framework.filter;


import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.util.Base64Encoder;
import com.framework.meteor.framework.util.MD5Util;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * 安全过滤器
 *
 * @author Meteor.wu
 * @since 2017/11/13 17:36
 */
@WebFilter(filterName = "SignFilter", urlPatterns = "/*")
public class SignFilter implements Filter {

    protected static String privateKey="ABCDEFG$";

    private static final List<String> mothedList = new ArrayList<String>(){{
        add("/runing/app/uploadRunningData");
        add("/version/app/getMaxVersionByClient");
        add("/appeal/app/getQAList");
        add("/appeal/app/getQAContent");
        add("/rule/app/getRuleTip");
        add("/nodefile/notify");
        add("/util/checkToken");
        add("/activity/pc/addActivity");
        add("/activity/pc/updateActivity");
        add("/activity/pc/addOfficalActivity");
        add("/activity/pc/updateOfficialActivity");
        add("/schoolUser/pc/getStudentList");
        add("/schoolUser/pc/downloadStudentList");
    }};

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if(servletRequest instanceof HttpServletRequest) {
            requestWrapper = new BodyReaderHttpServletRequestWrapper((HttpServletRequest) servletRequest);
        }
        if(null == requestWrapper) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (checkSign(requestWrapper)) {
                filterChain.doFilter(requestWrapper, servletResponse);
            } else {
                PrintWriter out = servletResponse.getWriter();
                String str = "{\"code\":"+ ResultMsg.SIGN_ERROR.getCode()+",\"message\":\"签名错误\",\"body\":null,\"exceptionDescription\":null}";
                out.print(str);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean checkSign(ServletRequest httpServletRequest) throws IOException {
        if (((HttpServletRequest) httpServletRequest).getMethod().equals("OPTIONS")) {
            return true;// CORS跨域请求，会看到两次请求记录，一次是option请求,一次是POST请求。。。option不需要签名
        }
        String resUrl = ((HttpServletRequest) httpServletRequest).getServletPath().toString();
        if (mothedList.contains(resUrl)) {
            return true;
        }
        if (((HttpServletRequest) httpServletRequest).getRequestURL().toString().contains("/download/Excel")) {
            return true;
        }
        // check sign
        BufferedReader br = httpServletRequest.getReader();
        StringBuilder bodyBuilder = new StringBuilder();
        String str;
        while((str = br.readLine()) != null){
            bodyBuilder.append(str);
        }

        String url = ((HttpServletRequest) httpServletRequest).getRequestURL()
                .append(httpServletRequest.getParameter("token") == null ? "" : "?token=" + httpServletRequest.getParameter("token"))
                .append(httpServletRequest.getParameter("token") == null ? "" : "&userId=" + httpServletRequest.getParameter("userId"))
                .append(httpServletRequest.getParameter("token") == null ? "?sign=" : "&sign=").toString();

        String sign = getSign(url, bodyBuilder.toString());
        String parameter = httpServletRequest.getParameter("sign");
        if (!parameter.equals(sign)) {
            return false;
        }
        return true;
    }

    private String getSign(String url, String body) {
        String bodyEncode = Base64Encoder.getBASE64(body);
        StringBuilder sign = new StringBuilder();
        sign.append(url).append(bodyEncode).append(privateKey);
        return MD5Util.getMD5(sign.toString());
    }
}
