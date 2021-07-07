package oqu.today.initital.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int user_id;
    private int course_id;
    private double price;
    @Size(max = 4000)
    private String info;
    private String status;

    public Account() {
    }

    public Account(int user_id, int course_id, double price, String info, String status) {
        this.user_id = user_id;
        this.course_id = course_id;
        this.price = price;
        this.info = info;
        this.status = status;
    }

    public Account(int id, int user_id, int course_id, double price, String info, String status) {
        this.id = id;
        this.user_id = user_id;
        this.course_id = course_id;
        this.price = price;
        this.info = info;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}