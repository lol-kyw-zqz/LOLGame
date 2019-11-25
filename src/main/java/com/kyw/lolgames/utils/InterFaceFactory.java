package com.kyw.lolgames.utils;

import java.util.ResourceBundle;

/**
 * @author : kangyw
 * @date : 下午 12:30 2019/11/25
 */
public class InterFaceFactory {

    public static <T> T getInstance(Class<T> c){
        //获取接口类名
        String interfaceName = c.getSimpleName();

        String interfaceImplName = ResourceBundle.getBundle("InterfaceFactory").getString(interfaceName).trim();

        //实例化具体对象
        try{
            System.out.println(interfaceName+"调用了实现类："+interfaceImplName);
            return (T) Class.forName(interfaceImplName).newInstance();
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e+"加载接口实例失败");
        }
    }
}
