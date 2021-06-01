package cn.edu.printer.pojo;

import java.util.Date;
import java.util.Objects;

public class Submission {
    private Integer id;
    private Date gmt_create;
    private User user;
    private String context;
    private Status status;
    private String ip;

    public Submission() {
        gmt_create = new Date();
        System.out.println(gmt_create);
        status = Status.等待打印;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmt_create() {
        return gmt_create;
    }

    public void setGmt_create(Date gmt_create) {
        this.gmt_create = gmt_create;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Submission{" +
                "id=" + id +
                ", gmt_create=" + gmt_create +
                ", user=" + user +
                ", context='" + context + '\'' +
                ", status=" + status +
                ", ip='" + ip + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Submission that = (Submission) o;
        return Objects.equals(id, that.id) && Objects.equals(gmt_create, that.gmt_create) && Objects.equals(user, that.user) && Objects.equals(context, that.context) && status == that.status && Objects.equals(ip, that.ip);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmt_create, user, context, status, ip);
    }
}
