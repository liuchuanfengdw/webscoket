/**
 * FileName: MsgVo
 * Author:   DING WEI
 * Date:     2019/1/25 16:55:55
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.webscoket.demo.entity;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 消息
 * @author DING WEI
 * @create 2019/1/25 16:55:55
 * @since 1.0.0
 */
public class MsgVo {

    /**
     * 0:提示消息
     * 1:真实消息
     * 2:上线
     */
    private String type;

    private String name;

    private String msg;

    private List<UserDo> users;

    public MsgVo() {
    }

    public MsgVo(String type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public MsgVo(String type, String name, String msg) {
        this.type = type;
        this.name = name;
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<UserDo> getUsers() {
        return users;
    }

    public void setUsers(List<UserDo> users) {
        this.users = users;
    }
}
