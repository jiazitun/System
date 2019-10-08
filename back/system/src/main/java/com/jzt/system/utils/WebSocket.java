package com.jzt.system.utils;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint("/websocket/{pageCode}")
@Component
public class WebSocket {


//    @Autowired
//    private  IRfidService rfidService;
//
//    {
//        System.out.println(rfidService.findAll().get(0).getRfidCard());
//    }

    private static final String loggerName=WebSocket.class.getName();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static Map<String, List<Session>> electricSocketMap = new ConcurrentHashMap<String, List<Session>>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("pageCode") String pageCode, Session session) {



        //this.iRfidService.findAll().get(0).getRfidCard();
        System.out.println("===============");
        List<Session> sessions = electricSocketMap.get(pageCode);
        if(null==sessions){
            List<Session> sessionList = new ArrayList<>();
            sessionList.add(session);
            electricSocketMap.put(pageCode,sessionList);
        }else{
            sessions.add(session);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("pageCode") String pageCode, Session session) {
        if (electricSocketMap.containsKey(pageCode)){
            electricSocketMap.get(pageCode).remove(session);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {


        System.out.println("websocket received message:"+message);
        try {
            File file=new File("/b.txt");
            FileInputStream input = new FileInputStream(file);
            byte[] bytes2 = new byte[1024];
            int len = input.read(bytes2);
            String messa=new String(bytes2, 0, len);
            System.out.println("文件中的信息是：" +messa);
            input.close();
            session.getBasicRemote().sendText(messa);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");;
    }


}