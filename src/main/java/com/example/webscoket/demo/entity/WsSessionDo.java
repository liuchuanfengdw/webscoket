/**
 * FileName: WsSessionDo
 * Author:   DING WEI
 * Date:     2019/1/29 13:48:48
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.example.webscoket.demo.entity;

import javax.websocket.Session;
import java.io.Serializable;

/**
 * 〈一句话功能简述〉<br>
 * @author DING WEI
 * @create 2019/1/29 13:48:48
 * @since 1.0.0
 */
public class WsSessionDo implements Serializable {

    private static final long serialVersionUID = 7809816424398447676L;

    private String driver;

    private Session session;

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
