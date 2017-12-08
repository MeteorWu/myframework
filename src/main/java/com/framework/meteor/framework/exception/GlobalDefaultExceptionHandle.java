package com.framework.meteor.framework.exception;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.ExceptionEntity;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.framework.meteor.framework.service.ExceptionService;
import com.framework.meteor.framework.util.DateUtil;
import com.framework.meteor.framework.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 */
@ControllerAdvice
@RestController
public class GlobalDefaultExceptionHandle extends ResponseEntityExceptionHandler {

    @Value("${adminlog.api.exception}")
    private String adminLogApiException;
    protected String adminLogApiString = "DEBUG";
    private Boolean adminLogApiFlag = false;
    protected Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ExceptionService exceptionService;

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String path = getExceptionPath(ex);
        if(StringUtil.isEmpty(path)) {
            path = ((ServletWebRequest)request).getRequest().getRequestURL()+"";
        }
        logger.info("请求地址：" + path);
        logger.error("异常信息：",ex);
        String errorMsg = ex.getMessage();
        if(StringUtil.isNotEmpty(errorMsg)) {
            if(errorMsg.length() > 500) {
                errorMsg = errorMsg.substring(0,500);
            }
        }
        ResponseBody response = new ResponseBody();
        String error = ex.toString();
        error = error.substring(0, error.indexOf(":"));
        response.setCode(status.value()+"");
        response.setError(error);
        response.setMessage(errorMsg);
        response.setPath(path);
        response.setTimestamp(DateUtil.getCurrentDateString());

        String userId = null;
        String clientType = null;
        String version = null;//版本号
        if(null != request.getParameter("userId")) {
            userId = request.getParameter("userId");
        }
        if(null != request.getParameter("t")) {
            clientType = request.getParameter("t");
        }
        if(null != request.getParameter("v")) {
            version = request.getParameter("v");
        }
        ExceptionEntity exceptionEntity = new ExceptionEntity(status.value()+"", errorMsg, DateUtil.getCurrentDateString(), error, path, userId, clientType, version);
        exceptionService.addException(exceptionEntity);
        response.setbody(null);
        return new ResponseEntity<Object>(response, status);
    }

    @ExceptionHandler(value= {ApiException.class, Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public Response exceptionHandle(HttpServletRequest request, Exception e){
        logger.error("异常信息：",e);
        String path = getExceptionPath(e);
        if(StringUtil.isEmpty(path)) {
            path = request.getRequestURL().toString();
        }
        ResponseBody response;

        if(e instanceof ApiException) {
            if(StringUtil.isNotEmpty(adminLogApiException) && adminLogApiException.equals(adminLogApiString)) {
                adminLogApiFlag = true;
            }
            String error = ((ApiException) e).getResultMsg().name();
            response = new ResponseBody( ((ApiException) e).resultMsg );
            response.setPath(path);
            response.setError(error);
        } else {
            adminLogApiFlag = true;
            String error = e.toString();
            String errorMsg = e.getMessage();
            if(StringUtil.isNotEmpty(error) && error.indexOf(":") > 0) {
                error = error.substring(0, error.indexOf(":"));
            }

            if(StringUtil.isNotEmpty(errorMsg)) {
                if(errorMsg.length() > 500) {
                    errorMsg = errorMsg.substring(0,500);
                }
            } else {
                errorMsg = error;
            }
            response = new ResponseBody();
            response.setError(error);
            response.setMessage(errorMsg);
            response.setCode(ResultMsg.INTERNAL_SERVER_ERROR.getCode());
            response.setTimestamp(DateUtil.getCurrentDateString());
            response.setPath(path);
        }
        if(adminLogApiFlag) {
            String userId = null;
            String clientType = null;
            String version = null;
            if(null != request.getParameter("userId")) {
                userId = request.getParameter("userId");
            }
            if(null != request.getParameter("t")) {
                clientType = request.getParameter("t");
            }
            if(null != request.getParameter("v")) {
                version = request.getParameter("v");
            }
            ExceptionEntity exceptionEntity = new ExceptionEntity(response.getCode(), response.getMessage(), DateUtil.getCurrentDateString(), response.getError(), path, userId, clientType, version);
            exceptionService.addException(exceptionEntity);
        }

        response.setbody(null);
        return response;
    }

    private String getExceptionPath(Exception ex) {
        StackTraceElement[] stackTraceElements = ex.getStackTrace();
        StringBuilder sb = new StringBuilder();
        String errString = null;
        int count = 0;
        for (int i = 0; i < stackTraceElements.length; i++) {
            StackTraceElement element = stackTraceElements[i];
            if (element.getClassName().contains("com.colorfulmall") && !element.getClassName().contains("$")) {
                sb.append("-").append(element.getClassName()).append("-").append(element.getLineNumber());
                System.out.println(element.getClassName());
                System.out.println(element.getLineNumber());
                count++;
            }
            if (count == 2) {
                break;
            }
        }
        if(StringUtil.isNotEmpty(sb.toString())) {
            if(sb.toString().startsWith("-")) {
                errString = sb.toString().substring(1, sb.toString().length());
            } else {
                errString = sb.toString();
            }
        }
        return errString;
    }

}
