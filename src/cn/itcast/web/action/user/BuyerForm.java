package cn.itcast.web.action.user;

import cn.itcast.web.formbean.BaseForm;

/**
 * desc:
 * User: weiguili(li5220008@gmail.com)
 * Date: 13-8-29
 * Time: 上午10:57
 */
public class BuyerForm extends BaseForm {
    private String username;
    private String password;
    private String email;
    private String realname;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }
}
