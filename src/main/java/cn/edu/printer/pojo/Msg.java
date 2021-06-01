package cn.edu.printer.pojo;

import java.util.Objects;

public class Msg {
    Boolean success;
    String msg;
    Object data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "success=" + success +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Msg msg1 = (Msg) o;
        return Objects.equals(success, msg1.success) && Objects.equals(msg, msg1.msg) && Objects.equals(data, msg1.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(success, msg, data);
    }
}
