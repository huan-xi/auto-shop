package xyz.huanxicloud.autoshop.common;

import java.io.Serializable;

public class ReturnMessage implements Serializable {
    private static final long serialVersionUID = -3307660546347616895L;
    private int status;//状态码
    private Object msg; //返回信息

    public int getStatus() {
        return status;
    }

    public ReturnMessage() {
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public ReturnMessage(int status, Object msg) {
        this.status = status;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "status"+Integer.toString(status)+"msg"+msg;
    }
}
