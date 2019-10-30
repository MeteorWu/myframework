package com.framework.meteor.framework.exception;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.model.Response;
import com.framework.meteor.framework.model.ResponseBody;
import com.framework.meteor.framework.service.ExceptionService;
import com.framework.meteor.framework.util.DateUtil;
import com.framework.meteor.framework.util.DateUtils;
import com.framework.meteor.framework.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 全局异常处理
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalDefaultExceptionHandle extends ResponseEntityExceptionHandler {

    @Value("${project.prefix}")
    private String projectPrefix;

    @Value("${adminlog.api.exception}")
    private String adminLogApiException;
    protected String adminLogApiString = "DEBUG";
    private Boolean adminLogApiFlag = false;

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

        ResponseBody response = null;
        if(ex instanceof MethodArgumentNotValidException){
            adminLogApiFlag = false;// 不需要存储的异常
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            BindingResult result = exception.getBindingResult();
            FieldError error = result.getFieldError();
            String field = error.getField();
            String code = error.getDefaultMessage();
            String message = String.format("%s:%s", field, code);
            log.warn("参数验证失败：" + message);

            response = new ResponseBody();
            response.setError(ResultMsg.PARAMETER_INCORRECT.getMsg());
            response.setMessage(message);
            response.setCode(ResultMsg.PARAMETER_INCORRECT.getCode());
            response.setTimestamp(DateUtils.getDateStringDefault(new Date()));
            response.setPath(path);
        } else {
            adminLogApiFlag = true;
            String errorMsg = ex.getMessage();
            if (StringUtil.isNotEmpty(errorMsg)) {
                if (errorMsg.length() > 500) {
                    errorMsg = errorMsg.substring(0, 500);
                }
            }

            response = new ResponseBody();
            String error = ex.toString();
            error = error.substring(0, error.indexOf(":"));
            response.setCode(status.value() + "");
            response.setError(error);
            response.setMessage(errorMsg);
            response.setPath(path);
            response.setTimestamp(DateUtils.getDateStringDefault(new Date()));
        }
        if(adminLogApiFlag) {
            exceptionService.addException(response, request.getParameter("userId"), request.getContextPath());
        }
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
            exceptionService.addException(response, request.getParameter("userId"), request.getContextPath());
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
            if (element.getClassName().contains(projectPrefix) && !element.getClassName().contains("$")) {
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
