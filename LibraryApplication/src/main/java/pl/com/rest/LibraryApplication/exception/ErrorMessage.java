package pl.com.rest.LibraryApplication.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;


public class ErrorMessage {

    private int status;
    private int code;
    private String message;
    private String link;
    private String developerMessage;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ErrorMessage(NotFoundException ex){
        this.status = Response.Status.NOT_FOUND.getStatusCode();
        this.message = ex.getMessage();
        this.link = "https://jersey.java.net/apidocs/2.8/jersey/javax/ws/rs/NotFoundException.html";
    }

    public ErrorMessage(AppException ex){
        this.code = ex.getCode();
        this.status = ex.getStatus();
        this.link = ex.getLink();
        this.developerMessage = ex.getDeveloperMessage();
        this.message = ex.getMessage();
    }

    public ErrorMessage() {}
}
