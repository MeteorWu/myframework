package com.framework.meteor.framework.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * token useId校验拦截器
 *
 * @author Meteor.wu
 * @since 2017/9/11 13:34
 */

public class AuthInterceptor extends HandlerInterceptorAdapter {

//    @Autowired
//    private UserDao userDao;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        checkSign(httpServletRequest);
//        checkToken(httpServletRequest);

        return true;
    }

    private boolean checkToken(HttpServletRequest httpServletRequest) throws Exception {
        // check token
//        String token = httpServletRequest.getParameter("token");
//        if (StringUtil.isEmpty(token)) {
//            throw new ApiException(ResultMsg.NO_TOKEN);
//        }
//
//        if (userDao == null) {
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(httpServletRequest.getServletContext());
//            userDao = (UserDao) factory.getBean("userDao");
//        }
//
//        User tokenUser = userDao.findByTokenAndEnabled(token, BooleanEnum.TRUE.ordinal());
//        if (tokenUser == null) {
//            throw new ApiException(ResultMsg.FAULT_TOKEN);
//        }
//        String  userId = httpServletRequest.getParameter("userId");
//        if (!tokenUser.getUserId().equals(userId)) {
//            throw new ApiException(ResultMsg.FAULT_TOKEN);
//        }

        return true;
    }

//    private void checkSign(HttpServletRequest httpServletRequest) throws IOException {
//        // check sign
//        BufferedReader br = httpServletRequest.getReader();
//        StringBuilder bodyBuilder = new StringBuilder();
//        String str;
//        while((str = br.readLine()) != null){
//            bodyBuilder.append(str);
//        }
//
//        String url = httpServletRequest.getRequestURL()
//                .append(httpServletRequest.getParameter("token") == null ? "" : "?token=" + httpServletRequest.getParameter("token"))
//                .append(httpServletRequest.getParameter("token") == null ? "" : "&userId=" + httpServletRequest.getParameter("userId"))
//                .append(httpServletRequest.getParameter("token") == null ? "?sign=" : "&sign=").toString();
//
//        String sign = SignFormat.getSignFormat(url, bodyBuilder.toString());
//        String parameter = httpServletRequest.getParameter("sign");
//        if (!parameter.equals(sign)) {
//            throw new ApiException(ResultMsg.SIGN_ERROR);
//        }
//    }

//    private static String getFullURL(HttpServletRequest request) {
//        StringBuffer requestURL = request.getRequestURL();
//        String queryString = request.getQueryString();
//        if (StringUtil.isEmpty(queryString)) {
//            return requestURL.toString();
//        } else {
//            return requestURL.append('?').append(queryString).toString();
//        }
//    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
