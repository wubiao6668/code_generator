package com.generator;

import com.generator.config.ApplicationConfig;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@Mojo(name = "gen")
public class GeneratorMojoFacade extends AbstractMojo {

    private Log log = getLog();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);


    }
}
