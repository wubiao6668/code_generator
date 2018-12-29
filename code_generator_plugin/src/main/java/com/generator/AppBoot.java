package com.generator;

import com.generator.context.Application;
import com.generator.server.HttpServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author wubiao
 */
public class AppBoot {
    public static void appStart() {
        try {
            System.out.println("***********************load spring begin*********************");
            Application.context.start();
            System.out.println("***********************load spring end*********************");
            HttpServer httpServer = Application.context.getBean(HttpServer.class);
            httpServer.start(1232);
//            System.out.println("输入任意字符退出!");
//            String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
