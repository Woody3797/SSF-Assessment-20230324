package ibf2022.batch2.ssf.frontcontroller.model;

import java.util.Random;

import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
    
    @NotNull
    @Size(min = 2, message = "Must be at least 2 characters in length")
    private String username;

    @NotBlank
    @Size(min = 2, message = "Must be at least 2 characters in length")
    private String password;

    private Boolean isAuthenticated = false;
    private Integer loginAttempts = 0;
    private String captcha;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsAuthenticated() {
        return isAuthenticated;
    }

    public void setIsAuthenticated(Boolean isAuthenticated) {
        this.isAuthenticated = isAuthenticated;
    }

    public Integer getLoginAttempts() {
        return loginAttempts;
    }

    public void setLoginAttempts(Integer loginAttempts) {
        this.loginAttempts = loginAttempts;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }


    public static User create(JsonObject jo) {
        User user = new User();
        user.setUsername(jo.getString("username"));
        user.setPassword(jo.getString("password"));
        return user;
    }

    public int createCaptcha() {
        Random rand = new Random();
        int num1 = rand.nextInt(50) + 1;
        return num1;
    }

    
}
