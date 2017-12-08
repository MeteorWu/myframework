package com.framework.meteor.framework.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRoleInteceptor extends HandlerInterceptorAdapter {

//    private  static  final Logger logger = Logger.getLogger(SystemLogAspect.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        response.setHeader("Content-Type", "application/json;charset=UTF-8");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
//        response.setHeader("Access-Control-Max-Age", "0");
//        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("XDomainRequestAllowed","1");
//
//
//        if (handler instanceof HandlerMethod) {
//            HandlerMethod methodHandler = (HandlerMethod) handler;
//            //首先获得方法上的注解
//            UserRoleAnnotation userRoleAnnotation = methodHandler.getMethodAnnotation(UserRoleAnnotation.class);
//
//            //首先获得方法上的注解，如果方法上没有写，再去获得类上的注解
//            if (null == userRoleAnnotation) {
//                Class objClass = methodHandler.getBeanType();
//                userRoleAnnotation = (UserRoleAnnotation) objClass.getAnnotation(UserRoleAnnotation.class);
//            }
//            if (null == userRoleAnnotation) {//如果接口不需要验证 @UserRoleAnnotation
//                return true;
//            }
//
//            if (StringUtil.isNotEmpty(request.getParameter("userId"))) {
//                User user = serviceUtils.userService.getById(request.getParameter("userId"));
//                if (null == user) {
//                    throw new ApiException(ResultMsg.USER_NOT_EXIST);//该用户不存在
//                }
//                Set<Integer> roleSet = new HashSet();
//                UserTypeEnum[] roles = userRoleAnnotation.roles();
//                for (UserTypeEnum type : roles) {
//                    roleSet.add(type.getCode());
//                }
//                if (roleSet.contains(user.getUserType())) {
//                    return true;
//                } else {
//                    throw new ApiException(ResultMsg.USER_ROLE_ERROR);//用户角色权限错误
//                }
//            } else {
//                throw new ApiException(ResultMsg.USERID_NOT_NULL);//用户ID不能为空或用户ID错误
//            }
//        } else {
//            return false;
//        }
        return  true;
    }
}
