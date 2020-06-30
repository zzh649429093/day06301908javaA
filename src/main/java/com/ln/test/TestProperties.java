package com.ln.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestProperties {

    /**
     * 读取解析properties文件
     */
    public static void readProperties() {
        //java.util包下专门做properties文件解析的类=》Properties
        Properties properties = new Properties();
        File file = new File("F:\\java第八个月知识点\\test.properties");
        //判断文件是否存在
        if(file.exists()) {
            InputStream inStream = null;
            try {
                inStream = new FileInputStream(file);
                //通过调用Properties的load方法，实现文件的加载、解析
                properties.load(inStream);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                if(inStream != null) {
                    try {
                        inStream.close();//关闭流
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            String username = properties.getProperty("username");
            System.out.println("username:"+username);
            System.out.println("password:"+properties.getProperty("password"));
            System.out.println("age:"+properties.getProperty("age"));
        }else {
            System.out.println("properties文件未找到，请核对提供文件路径！");
        }
    }

    public static void main(String[] args) {
        readProperties();
    }


}
