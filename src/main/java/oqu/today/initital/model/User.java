package oqu.today.initital.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId")
    private long id;
    private String name;
    private String email;
    private String phone;
    private String password;
    private String gender;
    private String activation;
    private int status;
    private String avatar;
    private Date bday;
    @OneToMany
    @JsonIgnore
    private List<Course> purchaised;

    public User() {
    }

    public User(String name, String email, String phone, String password, String gender, String activation, int status, String avatar, Date bday, List<Course> purchaised) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.gender = gender;
        this.activation = activation;
        this.status = status;
        this.avatar = avatar;
        this.bday = bday;
        this.purchaised = purchaised;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getBday() {
        return bday;
    }

    public void setBday(Date bday) {
        this.bday = bday;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", activation='" + activation + '\'' +
                ", status=" + status +
                ", avatar='" + avatar + '\'' +
                ", bday=" + bday.toString() +
                '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Course> getPurchaised() {
        return purchaised;
    }

    public void setPurchaised(List<Course> purchaised) {
        this.purchaised = purchaised;
    }
}