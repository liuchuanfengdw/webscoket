/**
 * FileName: UserDo
 * Author:   DING WEI
 * Date:     2019/2/11 13:51:51
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.webscoket.demo.entity;

/**
 * 〈一句话功能简述〉<br>
 * 用户
 * @author DING WEI
 * @create 2019/2/11 13:51:51
 * @since 1.0.0
 */
public class UserDo {

    private String id;

    private String userName;

    private String password;

    private String sex;

    public UserDo() {
    }

    public UserDo(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
