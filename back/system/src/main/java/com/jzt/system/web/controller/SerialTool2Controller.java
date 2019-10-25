package com.jzt.system.web.controller;


import com.jzt.system.bean.Drug;
import com.jzt.system.service.IDrugService;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import gnu.io.*;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.TooManyListenersException;



@RestController
@RequestMapping("/Hello")
public class SerialTool2Controller {
    @Resource
    private IDrugService clothesService;
    private static String driver = "com.mysql.cj.jdbc.Driver";
    private static String url = "jdbc:mysql://127.0.0.1/sys?serverTimezone=GMT%2B8";
    private static String user = "root";
    private static String password = "root";
    private static SerialTool2Controller SerialTool2Controller=null;
    static SerialPort serialPort;
    //类一加载进来就进行SerialTool2Controller的初始化
    static {
        if(SerialTool2Controller==null) {
            SerialTool2Controller=new SerialTool2Controller();
        }
    }

    private SerialTool2Controller() {

    }

    public static SerialTool2Controller getSerialTool2Controller() {
        if(SerialTool2Controller==null) {
            SerialTool2Controller=new SerialTool2Controller();
        }
        return SerialTool2Controller;
    }
    /**
     * 查找所有可用端口
     * @return
     */
    public static final ArrayList<String> findPort(){
        Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
        ArrayList<String> portNameList = new ArrayList<>();
        while(portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
        }
        return portNameList;
    }

    public static final SerialPort openPort(String portName, int baudrate){
        //通过端口名识别端口
        CommPortIdentifier portIdentifier = null;
        try {
            portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException e1) {
            e1.printStackTrace();
        }
        //打开端口，并给端口名字和一个timeout（打开操作的超时时间）
        CommPort commPort = null;
        try {
            commPort = portIdentifier.open(portName, 100);
        } catch (PortInUseException e1) {
            e1.printStackTrace();
        }
        //判断是不是串口
        if(commPort instanceof SerialPort) {
            SerialPort serialPort=(SerialPort)commPort;
            //设置一下串口的波特率等参数
            try {
                serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            } catch (UnsupportedCommOperationException e) {

                e.printStackTrace();
            }
            return serialPort;
        }else {
            return null;
        }

    }

    //关闭串口

    public static Message closePort(SerialPort serialPort) {
        if(serialPort!=null) {
            serialPort.close();
            serialPort=null;
        }
        return MessageUtils.success("成功停止！");
    }
    @PostMapping("/close")
    public void Close0() {
        serialPort.close();
    }
    //往串口发送数据
    public static void sendToPort(SerialPort serialPort,byte[] order) {
        OutputStream out=null;
        try {
            out=serialPort.getOutputStream();
            out.write(order);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(out!=null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //从串口读取数据
    public static JSONObject readFromPort(SerialPort serialPort) {
        InputStream inputStream = null;
        BufferedReader br = null;

        String string = null;
        JSONObject jsonData = null;
        try {
            inputStream = serialPort.getInputStream();
            // 通过输入流对象的available方法获取数组字节长度
            br= new BufferedReader(new InputStreamReader(inputStream));
            if((string = br.readLine())!=null) {
//                System.out.println("\""+string+"\"");
                jsonData=JSONObject.fromObject(string);
//                System.out.println("dasdas"+jsonData);
                return jsonData;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                    inputStream = null;
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return jsonData;
    }

    //添加监听器
    public static void addListener(SerialPort port,SerialPortEventListener listener) {


        //给串口添加监听器
        try {
            port.addEventListener(listener);
        } catch (TooManyListenersException e) {
            e.printStackTrace();
        }
        //设置当有数据到达时唤醒监听接收线程
        port.notifyOnDataAvailable(true);
        //设置当通信中断时唤醒中断线程
        port.notifyOnBreakInterrupt(true);


    }

    @PostMapping("Run")
    public Message Start(Drug clothes) {


        ArrayList<String> list = findPort();
       // ParamConfig paramConfig = new ParamConfig("COM5", 57600, 0, 8, 1);
        serialPort = openPort(list.get(0),57600);
        //向串口发送数据
//        String a="hello world234";
//        byte[] bytes = a.getBytes();
//        sendToPort(serialPort,bytes);

        //匿名内部类，实现SerialPortEventListener进行监听serialPort!=null持续监听读取串口信息
        addListener(serialPort,new SerialPortEventListener() {


            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                Connection conn = null;
                Statement stmt = null;


                switch(serialPortEvent.getEventType()) {
                    case SerialPortEvent.BI://通讯中断
                    case SerialPortEvent.OE: // 7 溢位（溢出）错误

                    case SerialPortEvent.FE: // 9 帧错误

                    case SerialPortEvent.PE: // 8 奇偶校验错误

                    case SerialPortEvent.CD: // 6 载波检测

                    case SerialPortEvent.CTS: // 3 清除待发送数据

                    case SerialPortEvent.DSR: // 4 待发送数据准备好了

                    case SerialPortEvent.RI: // 5 振铃指示

                    case SerialPortEvent.OUTPUT_BUFFER_EMPTY: // 2 输出缓冲区已清空
                        break;
                    case SerialPortEvent.DATA_AVAILABLE: // 1 串口存在可用数据
                        byte[] data = null;
                        if(serialPort!=null) {
                            JSONObject jsonData = JSONObject.fromObject(readFromPort (serialPort));
                            JSONObject jb = JSONObject.fromObject(jsonData);
                            String loginname = (String) jb.get("tagId");
                            if(loginname!=null) {

                                //Clothes clothes1= new Clothes();
                               // clothes1.setcId(clothes.getcId());
                                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                                clothes.setRfid(loginname);
                                clothesService.saveOrUpdate(clothes);

//                                if(clothes.getcName()!=null) {
//                                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//                                    clothes1.setTime(df.format(new Date()));
//                                    System.out.println(df.format(new Date()));
//                                    clothesService.saveOrUpdate(clothes);
//                                    clothesService.updateByPrimaryKey(clothes1);
//                                }
                            }
                            System.out.println(loginname);

                        }
                }

            }

        });

        return MessageUtils.success("操作成功");
        //关闭串口，持续监听下请勿关闭串口
        //serialPort.close();


    }

    @PostMapping("Stop")
    public Message Stop() {
        ArrayList<String> list = findPort();
        if(serialPort!=null) {
            serialPort.close();
            serialPort=null;
        }
        return MessageUtils.success("成功停止！");
    }

}
