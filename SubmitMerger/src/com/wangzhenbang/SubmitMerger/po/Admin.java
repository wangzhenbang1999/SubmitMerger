package com.wangzhenbang.SubmitMerger.po;

public class Admin {
    /**
     * 主键
     */
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    private int adminType;
    private int status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getAdminType() {
        return adminType;
    }
    public void setAdminType(int adminType) {
        this.adminType = adminType;
    }
    @Override
    public String toString() {
        return "Admin [username=" + username + ", password=" + password + ", nickname=" + nickname + "]";
    }

}
