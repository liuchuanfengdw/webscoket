package com.example.webscoket.demo.enums;

/**
 * FileName: RedisKeysEnum
 * Author:   DING WEI
 * Date:     2019/2/1 9:54:54
 * Description: TODO
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public enum RedisKeysEnum {

    WEB_SOCKET_SESSION_KEY( "web_socket_session","websocket的session的hashKey");

    /**
     * @FieldName code 编码
     */
    private String code;

    /**
     * @FieldName 信息
     */
    private String msg;


    /**
     * @Title:  ResultEnum
     * @Description:    构造
     * @param:  @param code
     * @param:  @param msg
     * @throws
     */
    private RedisKeysEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @Title:  getCode <BR>
     * @Description: 编码 <BR>
     * @return: Integer <BR>
     */
    public String getCode() {
        return code;
    }

    /**
     * @Title:  getMsg <BR>
     * @Description: 信息 <BR>
     * @return: String <BR>
     */
    public String getMsg() {
        return msg;
    }


    /**
     * @Title: getMsg
     * @Description: TODO(根据枚举code直接获取错误说明)
     * @author: cnbizdk
     * @param: @param code
     * @param: @return
     * @return: String
     * @throws
     */
    public static String getMsg(Integer code) {
        for (RedisKeysEnum c : RedisKeysEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.getMsg();
            }
        }
        return null;
    }


}
