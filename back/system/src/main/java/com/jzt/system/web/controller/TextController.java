package com.jzt.system.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jzt.system.utils.Message;
import com.jzt.system.utils.MessageUtils;
import gnu.io.*;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.TooManyListenersException;
@RestController
public class TextController implements SerialPortEventListener {

    // 检测系统中可用的通讯端口类
    private CommPortIdentifier portId;

    // 枚举类型
    private Enumeration<CommPortIdentifier> portList;

    // RS232串口
    private SerialPort serialPort;

    // 输入输出流
    private InputStream inputStream;
    private OutputStream outputStream;

    // 保存串口返回信息
    private String test;

    // 保存串口返回信息十六进制
//    private String dataHex;

    // 初始化串口
    @SuppressWarnings("unchecked")
    public void init(){
        // 获取系统中的所有通讯端口
        portList = CommPortIdentifier.getPortIdentifiers();
        // 循环通讯端口
        while(portList.hasMoreElements()){
            portId = portList.nextElement();
            // 判断是否是串口
            if(portId.getPortType() == CommPortIdentifier.PORT_SERIAL){
                // 比较串口名称是否是"COM6"
                if("COM3".equals(portId.getName())){
                    System.out.println("找到串口 COM6");
                    // 打开串口
                    try{
                        // open: (应用程序名[命名随意],阻塞时等待的毫秒数)
                        serialPort = (SerialPort) portId.open(Object.class.getSimpleName(),2000);
                        System.out.println("获取到串口对象,COM6");
                        // 设置串口监听
                        serialPort.addEventListener(this);
                        // 设置窗口数据时间有效(可监听)
                        serialPort.notifyOnDataAvailable(true);
                        // 设置串口通讯参数
                        // 波特率,数据位,停止位和校验方式
                        serialPort.setSerialPortParams(9600,SerialPort.DATABITS_8,SerialPort.STOPBITS_1,SerialPort.PARITY_NONE);
                        test = "";
                        outputStream = serialPort.getOutputStream();
                        System.out.println(outputStream);
                    }catch (PortInUseException e){
                        e.printStackTrace();
                    }catch (TooManyListenersException e){
                        e.printStackTrace();
                    }catch (UnsupportedCommOperationException e){
                        e.printStackTrace();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    // 结束循环
                    break;
                }
            }
        }
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        System.out.println("我觉得serialEvent这个方法没有走");
        switch(serialPortEvent.getEventType()){
            case SerialPortEvent.BI: //通讯中断
            case SerialPortEvent.OE: //溢位错误
            case SerialPortEvent.FE: //帧错误
            case SerialPortEvent.PE: //奇偶校验错误
            case SerialPortEvent.CD: //载波错误
            case SerialPortEvent.CTS: //清除发送
            case SerialPortEvent.DSR: //数据设备准备好
            case SerialPortEvent.RI: //响铃检测
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY: //输出缓冲区已清空
                break;
            case SerialPortEvent.DATA_AVAILABLE: //有数据到达
//                readComm();
                String mess = readComm();
                if(mess != null){
                    JSONObject jsonObject = JSON.parseObject(mess);
                    System.out.println("转换后的数据:" + jsonObject);
                }
                break;
            default:
                break;
        }
    }

    // 读取串口返回信息
        public String readComm(){
        System.out.println("我觉得readComm这个方法没有走");
        byte[] readBuffer = new byte[1024];
        try{
            inputStream = serialPort.getInputStream();
            // 从线路上读取数据流
            int len = 0;
            while ((len = inputStream.read(readBuffer)) != -1){
                test += new String(readBuffer,0,len).trim();
//                if(test != null){
//                    JSONObject jsonObject = JSON.parseObject(test);
//                    System.out.println("转换后的数据:" + jsonObject);
//                }
                inputStream.close();
                break;
            }
            if(!test.equals("")) {
                System.out.println("data" + test);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return test;
    }

    // 关闭串口
    public void closeSerialPort(){
        if(serialPort != null){
            serialPort.notifyOnDataAvailable(false);
            serialPort.removeEventListener();
            if(inputStream != null){
                try{
                    inputStream.close();
                    inputStream = null;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try{
                    outputStream.close();
                    outputStream = null;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            serialPort.close();
            serialPort = null;
        }
    }

//    public void sendComm(String data) throws Exception {
//        byte[] writerBuffer = null;
//        try {
//            writerBuffer = hexToByteArray(data);
//        } catch (NumberFormatException e) {
//            throw new Exception("命令格式错误！");
//        }
//        try {
//            outputStream = serialPort.getOutputStream();
//            outputStream.write(writerBuffer);
//            outputStream.flush();
//        } catch (NullPointerException e) {
//            throw new Exception("找不到串口。");
//        } catch (IOException e) {
//            throw new Exception("发送信息到串口时发生IO异常");
//        }
//    }
//
//    /**
//     * 十六进制串口返回值获取
//     */
//    public String getDataHex() {
//        String result = dataHex;
//        // 置空执行结果
//        dataHex = null;
//        // 返回执行结果
//        return result;
//    }
//
//    /**
//     * 串口返回值获取
//     */
//    public String getData() {
//        String result = test;
//        // 置空执行结果
//        test = null;
//        // 返回执行结果
//        return result;
//    }
//
//
//
//
//
//    /**
//     * Hex字符串转byte
//     *
//     * @param inHex 待转换的Hex字符串
//     * @return 转换后的byte
//     */
//    public static byte hexToByte(String inHex) {
//        return (byte) Integer.parseInt(inHex, 16);
//    }
//
//    /**
//     * hex字符串转byte数组
//     *
//     * @param inHex 待转换的Hex字符串
//     * @return 转换后的byte数组结果
//     */
//    public static byte[] hexToByteArray(String inHex) {
//        int hexlen = inHex.length();
//        byte[] result;
//        if (hexlen % 2 == 1) {
//            // 奇数
//            hexlen++;
//            result = new byte[(hexlen / 2)];
//            inHex = "0" + inHex;
//        } else {
//            // 偶数
//            result = new byte[(hexlen / 2)];
//        }
//        int j = 0;
//        for (int i = 0; i < hexlen; i += 2) {
//            result[j] = hexToByte(inHex.substring(i, i + 2));
//            j++;
//        }
//        return result;
//    }
//
//    /**
//     * 数组转换成十六进制字符串
//     *
//     * @parambyte[]
//     * @return HexString
//     */
//    public static final String bytesToHexString(byte[] bArray) {
//        StringBuffer sb = new StringBuffer(bArray.length);
//        String sTemp;
//        for (int i = 0; i < bArray.length; i++) {
//            sTemp = Integer.toHexString(0xFF & bArray[i]);
//            if (sTemp.length() < 2)
//                sb.append(0);
//            sb.append(sTemp.toUpperCase());
//        }
//        return sb.toString();
//    }

    @RequestMapping("/testRead")
    public void Message() throws Exception {

        init();
        System.out.println("输出" + test);
//        closeSerialPort();

    }
    @GetMapping("RerurnMessage")
    private Message RerurnMessage(){
        String mess=readComm();
        if(mess!=null)
        {
            System.out.println("mess"+mess);
            /*
            MyClass myClass= (MyClass) JSONObject.toBean(jsonObject,MyClass.class);
            System.out.println(myClass.getDataType());
            System.out.println(myClass.getTagId());*/
            //多数据转json的格式
            JSONArray jsonarray = JSONArray.fromObject(mess);
        }

        return MessageUtils.success("刷卡成功");
    }

}
