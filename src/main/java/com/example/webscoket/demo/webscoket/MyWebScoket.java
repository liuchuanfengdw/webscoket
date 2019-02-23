package com.example.webscoket.demo.webscoket;

import com.alibaba.druid.sql.dialect.mysql.visitor.transform.NameResolveVisitor;
import com.alibaba.fastjson.JSON;
import com.example.webscoket.demo.Cache.OnlineCache;
import com.example.webscoket.demo.configuation.MyEndpointConfigure;
import com.example.webscoket.demo.entity.MsgVo;
import com.example.webscoket.demo.entity.SocketEntity;
import com.example.webscoket.demo.entity.UserDo;
import com.example.webscoket.demo.enums.RedisKeysEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 虽然@component默认是单例模式的，
 * 但在spring boot 中还是会为每一个webscoket连接初始化一个bean，
 * 所以这里使用一个静态的set保存spring boot创建的bean--MyWebscoket
 */
@ServerEndpoint ( value="/websocket/{name}" ,configurator= MyEndpointConfigure.class)
@Component
public class MyWebScoket {
    /**
     * 用来存储每个客户端对应的MyWebScoket对象
     */
    private static CopyOnWriteArraySet<MyWebScoket> webScoketset = new CopyOnWriteArraySet <MyWebScoket> (  );
    /**
     * 用来记录sessionid和session之间的绑定关系。
     */
    private static Map<String,Session> map = new HashMap <String,Session> ();
    private Session session;
    private String name;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 成功建立连接调用的方法
     */
    @OnOpen
    public void onOpen( Session session, @PathParam  ( "name" ) String name){
        this.session = session;
        this.name = name;
        map.put ( name,session );
        webScoketset.add(this);
        MsgVo msgVo = new MsgVo("0","【"+name+"】上线了,当前在线人数为："+map.size ());
//        this.session.getAsyncRemote().sendText(JSON.toJSONString(msgVo));
        try {
            session.getBasicRemote().sendText(JSON.toJSONString(msgVo));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pushOnline(name,msgVo);
        //将链接信息存到redis
        redisTemplate.opsForHash().put(RedisKeysEnum.WEB_SOCKET_SESSION_KEY.getCode(),name,JSON.toJSONString(session.getId ()));
    }

    /**
     * 连接关闭调用的方法
     * @param session
     */
    @OnClose
    public void onClose(Session session){
        webScoketset.remove ( this );
        map.remove (name);
        //将链接信息从redis删除
        redisTemplate.opsForHash().delete(RedisKeysEnum.WEB_SOCKET_SESSION_KEY.getCode(),name);
        List<UserDo> online = OnlineCache.online;
        for(int i =0;i<online.size();i++){
            if(online.get(i).getUserName().equals(name)){
                online.remove(i);
                break;
            }
        }
        MsgVo msgVo = new MsgVo("0","【"+name+"】下线了,当前在线人数为："+map.size ());
        if(map.size()> 0){
            pushOnline(name,msgVo);
        }
    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        //将链接信息从redis删除
        redisTemplate.opsForHash().delete(RedisKeysEnum.WEB_SOCKET_SESSION_KEY.getCode(),name);
        error.printStackTrace ();
    }

    /**
     * 收到客户消息后调用的方法
     * @param session
     */
    @OnMessage
    public void OnMessage(String message,Session session, @PathParam  ( "name" ) String name) throws IOException {
        //message不是普通的string,而是我们定义的SocketEntity,json字符串
        SocketEntity socketEntity = new ObjectMapper (  ).readValue ( message,SocketEntity.class );
        //单聊
        if (socketEntity.getType ()==1){
            //单聊：需要找到发送者和接收者即可
            Session fromsession = map.get (name);
            Session tosession = map.get ( socketEntity.getToUser ());
            if (tosession!=null){
                MsgVo msgVo = new MsgVo("1",name,socketEntity.getMessage ());
                fromsession.getAsyncRemote ().sendText (JSON.toJSONString(msgVo));
                tosession.getAsyncRemote ().sendText (JSON.toJSONString(msgVo));
            }else {
                MsgVo msgVo = new MsgVo("0","系统消息:对方不在线");
                fromsession.getAsyncRemote ().sendText (JSON.toJSONString(msgVo));
            }
        }else {
            //   广播群发给每一个客户端
            broadcast(socketEntity,name);
        }
    }

    /**
     * 群发消息
     * @param socketEntity
     */
    private void broadcast(SocketEntity socketEntity ,String name) {
        for (MyWebScoket myWebScoket:webScoketset){
            //发送消息
            MsgVo msgVo = new MsgVo("1",name,socketEntity.getMessage ());
            myWebScoket.session.getAsyncRemote ().sendText (JSON.toJSONString(msgVo));
        }
    }

    /**
     * 推送上线
     */
    private void pushOnline(String name,MsgVo msgVo1) {
        for (Map.Entry entry:map.entrySet()){
            Session session1 = (Session) entry.getValue();
            //发送消息
            MsgVo msgVo = new MsgVo("2","");
            List<UserDo> online = OnlineCache.online;
            msgVo.setUsers(online);
            //
            try {
                session1.getBasicRemote().sendText(JSON.toJSONString(msgVo));
                if(!entry.getKey().equals(name)){
                    session1.getBasicRemote().sendText(JSON.toJSONString(msgVo1));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

//上传上去了
}
