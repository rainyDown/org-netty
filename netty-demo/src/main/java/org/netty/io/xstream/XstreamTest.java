package org.netty.io.xstream;

import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

public class XstreamTest {

    public static void main1(String[] args) throws IOException {
        File xmlFile;
        FileChannel fileChannel = null;
        FileInputStream fileInputStream = null;
        try {
            xmlFile = new File("D:\\" + "deviceMapping.xml");
            fileInputStream = new FileInputStream(xmlFile);
            fileChannel = fileInputStream.getChannel();
            int len = 0;
            StringBuilder xml = new StringBuilder();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            while ((len = fileChannel.read(byteBuffer)) != -1) {
                //byteBuffer.flip();
                xml.append(new String(byteBuffer.array(), 0, len));
            }
            System.out.println(xml.toString());
            XStream stream = new XStream();
            //stream.autodetectAnnotations(true);
            stream.processAnnotations(DeviceMacs.class);
            DeviceMacs deviceMacs = (DeviceMacs) stream.fromXML(xml.toString());
            System.out.println(deviceMacs.toString());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            //log.info("read xml error :{}", e.getMessage());
        } finally {
            if (fileInputStream != null){
                fileInputStream.close();
            }
            if (fileChannel != null){
                fileChannel.close();
            }
        }
    }

    public static void main(String[] args) throws IllegalAccessException {
        DeviceMacs.deviceMapping mapping = new DeviceMacs.deviceMapping();

        mapping.setMac("1");
        mapping.setSn("001");
        jsonClz2Map(mapping);
    }


    public static Map<String, Object> jsonClz2Map(Object object) throws IllegalAccessException {
        Map<String, Object> params = new HashMap<>();
        Class<?> clazz = object.getClass();
        for (Field field : clazz.getDeclaredFields()){
            field.setAccessible(true);
            params.put(field.getName(), field.get(object));
        }
        System.out.println(params.entrySet());
        return params;
    }
}
