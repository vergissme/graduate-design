package org.example.nfchats.exception;

/**
 * Description 自定义异常
 * ProjectName imagetool
 * Version 1.0
 */

public class ImageTypeException extends Throwable {
    private String zh_msg;
    public ImageTypeException() {
    }

    public ImageTypeException(String message,String zh_msg) {
        super(message);
        this.zh_msg = zh_msg;
    }

    public String getZh_msg() {
        return zh_msg;
    }

    public void setZh_msg(String zh_msg) {
        this.zh_msg = zh_msg;
    }
}
