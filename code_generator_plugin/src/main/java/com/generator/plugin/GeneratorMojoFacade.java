package com.generator.plugin;

import io.netty.channel.ChannelFuture;
import com.generator.server.HttpServer;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;

import java.net.InetSocketAddress;


/**
 * @author wubiao
 */
@Mojo(name = "gen")
public class GeneratorMojoFacade extends AbstractMojo {

    private Log log = getLog();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println("123131221212");
        String[] args = new String[]{"8787"};
        if (args.length != 1) {
            System.err.println("Please give port as argument");
            System.exit(1);
        }
        int port = Integer.parseInt(args[0]);
        final HttpServer endpoint = new HttpServer();
        ChannelFuture future = endpoint.start(new InetSocketAddress(port));
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                endpoint.destroy();
            }
        });
        future.channel().closeFuture().syncUninterruptibly();
    }
}
