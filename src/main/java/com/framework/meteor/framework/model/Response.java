package com.framework.meteor.framework.model;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.framework.meteor.framework.constant.ResultMsg;


@JsonPropertyOrder({"status, error, message, timestamp, path"})//排序
public class Response {
	private String code;
	private String error;
	private String message;
	private String timestamp;
	private String path;

	public Response() {
	}

	public Response(ResultMsg resultMsg){
		this.code = resultMsg.getCode();
		this.message = resultMsg.getMsg();
//		this.timestamp = DateUtils.getDateStringDefault(new Date());
	}

	public Response(String code, String msg) {
		this.code = code;
		this.message = msg;
//        this.timestamp = DateUtils.getDateStringDefault(new Date());
	}

	public static Response error() {
		return bulid(ResultMsg.SYSTEM_ERROR);
	}
	public static Response success() {
		return bulid(ResultMsg.SUCCESS);
	}

	public static Response bulid(ResultMsg resultMsg) {
		return new Response(resultMsg);
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	//	@Override
//	public String toString() {
//		return "ApiResponse { "code=" + code + ", error=" + error + ", message=" + message + ", path=" + path + "}";
//	}
}
