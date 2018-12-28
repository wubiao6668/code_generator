package com.generator;

import com.generator.config.RootConfig;
import com.generator.server.HttpServer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author wubiao
 */
public class AppBoot {
   public static AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RootConfig.class);
    public static void appStart() {
        System.out.println("***********************load spring begin*********************");
        applicationContext.start();
        System.out.println("***********************load spring end*********************");
        HttpServer httpServer = applicationContext.getBean(HttpServer.class);
        try {
            httpServer.start(1232);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
