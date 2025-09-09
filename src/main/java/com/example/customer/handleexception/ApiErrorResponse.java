package com.example.customer.handleexception;


import java.util.Date;

public class ApiErrorResponse {

	private Date date;
    private int status;
    private String error;
    private String message;
    private String path;
	public ApiErrorResponse() {
		super();
	}
	public ApiErrorResponse(Date date, int status, String error, String message, String path) {
		super();
		this.date = date;
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "ApiErrorResponse [date=" + date + ", status=" + status + ", error=" + error + ", message=" + message
				+ ", path=" + path + "]";
	}
    
	
}
