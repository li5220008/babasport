package cn.itcast.bean.user;

import org.junit.Test;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Desc: 用户实体
 * @author : weiguili(li5220008@gmail.com)
 * Date: 13-8-25
 * Time: 上午11:19
 */
@Entity
public class Buyer {
    /* 用户名 */
    private String username;
    /* 密码 */
    private String password;//MD5
    /* 电子邮件 */
    private String email;

    @Id
    @Column(length=20)
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @Column(length=32, nullable=false)
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Column(length=45,nullable=false)
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((username == null) ? 0 : username.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Buyer other = (Buyer) obj;
        if (username == null) {
            if (other.username != null)
                return false;
        } else if (!username.equals(other.username))
            return false;
        return true;
    }

}
