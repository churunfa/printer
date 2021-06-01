package cn.edu.printer.pojo;

import java.util.Date;
import java.util.Objects;

public class User {
    private Integer id;
    private Date gmt_create;
    private Date last_login;
    private String username;
    private String password;
    private String show_name;
    private String seat_number;

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

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShow_name() {
        return show_name;
    }

    public void setShow_name(String show_name) {
        this.show_name = show_name;
    }

    public String getSeat_number() {
        return seat_number;
    }

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", gmt_create=" + gmt_create +
                ", last_login=" + last_login +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", show_name='" + show_name + '\'' +
                ", seat_number='" + seat_number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(gmt_create, user.gmt_create) && Objects.equals(last_login, user.last_login) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(show_name, user.show_name) && Objects.equals(seat_number, user.seat_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gmt_create, last_login, username, password, show_name, seat_number);
    }
}
