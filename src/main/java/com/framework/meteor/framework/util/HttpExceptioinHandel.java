package com.framework.meteor.framework.util;

import com.framework.meteor.framework.constant.ResultMsg;
import com.framework.meteor.framework.exception.ApiException;
import com.framework.meteor.framework.model.HttpException;
import com.framework.meteor.framework.service.HttpExceptionService;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HttpExceptioinHandel {

    @Autowired
    private HttpExceptionService httpExceptionService;

    public JSONObject getObjectInfo(String url, String params) {
        try {
            String strData = HttpRequestUtil.httpPostWithJSON(url, params);
            if (StringUtils.isEmpty(strData)) {
                HttpException httpException = new HttpException(url, params, ResultMsg.RESPONSE_ERROR.getMsg(), "request fail");
                httpExceptionService.insert(httpException);
                throw new ApiException(ResultMsg.RESPONSE_ERROR);
            }
            JSONObject res = JSONObject.fromObject(strData);
            if (!res.getString("code").equals("200")) {
                HttpException httpException = new HttpException(url, params, res.getString("msg"), "invalid response data");
                httpExceptionService.insert(httpException);
                throw new ApiException(ResultMsg.RESPONSE_FAIL);
            }

//            throwApiExceptionWhenFail(url, "POST", params, res, "code", "200", );
            return res.getJSONObject("object");// 只允许传object
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 集中处理http请求失败，统一throw api exception
     * @param json json对象
     * @param statusKey 状态key eg：code or success or status
     * @param statusSucVaule 200? success
     * @param status 失败状态，系统内自定义 resultMsg
     * @param messageKey 描述信息的key
     */
    public void throwApiExceptionWhenFail(String url, String method, String params, JSONObject json,
                                          String statusKey, String statusSucVaule, String status,
                                          String messageKey) {
        //    *{"Data":null,"Success":false,"Code":null,"Message":"请传入tradeNo","IsBusinessException":false}
        //    *{"code":null,"msg":"请传入tradeNo","object":null}
        if (json == null || json.size() == 0) {
            HttpException httpException = new HttpException(url,  params, ResultMsg.RESPONSE_ERROR.getMsg(), "request fail");
            httpExceptionService.insert(httpException);
            throw new ApiException(ResultMsg.RESPONSE_ERROR);
        }
        if (!json.containsKey(statusKey) || !json.containsKey(messageKey)) {
            HttpException httpException = new HttpException(url,  params, ResultMsg.RESPONSE_PARAMS_ERROR.getMsg(), "request fail");
            httpExceptionService.insert(httpException);
            throw new ApiException(ResultMsg.RESPONSE_PARAMS_ERROR);
        }
        if (!json.getString(statusKey).equals(statusSucVaule)) {
            throw new ApiException(status, json.getString(messageKey));
        }
    }
}
