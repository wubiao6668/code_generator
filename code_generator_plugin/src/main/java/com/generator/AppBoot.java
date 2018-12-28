package com.generator;

import com.generator.context.Application;
import com.generator.server.HttpServer;

/**
 * @author wubiao
 */
public class AppBoot {
    public static void appStart() {
        System.out.println("***********************load spring begin*********************");
        Application.context.start();
        System.out.println("***********************load spring end*********************");
        HttpServer httpServer = Application.context.getBean(HttpServer.class);
        try {
            httpServer.start(1232);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
