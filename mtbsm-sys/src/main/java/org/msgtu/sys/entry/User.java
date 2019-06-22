package org.msgtu.sys.entry;

import org.msgtu.common.entry.BaseEntry;

/**
 * Created by Wonshine on 2017-06-27.
 */
public class User extends BaseEntry{
    private static final long serialVersionUID = 3458038539435816986L;
    private Long userId;
    private String userLogin;
    private String userName;
    private String userPsw;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsw() {
        return userPsw;
    }

    public void setUserPsw(String userPsw) {
        this.userPsw = userPsw;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userLogin=" + userLogin +
                ", userName=" + userName +
                ", userPsw=" + userPsw +
                '}';
    }
}

