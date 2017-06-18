package pl.com.rest.LibraryApplication.exception;


public class AppException extends Exception {

    private Integer status;

    /** application specific error code */
    private int code;

    /** link documenting the exception */
    private String link;

    /** detailed error description for developers*/
    private String developerMessage;

    public AppException(int status, int code, String message, String developerMessage, String link) {
        super(message);
        this.status = status;
        this.code = code;
        this.developerMessage = developerMessage;
        this.link = link;
    }


    public AppException() { }

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




}
