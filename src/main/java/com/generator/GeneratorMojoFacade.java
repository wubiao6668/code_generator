package com.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Mojo;


@Mojo(name = "gen")
public class GeneratorMojoFacade extends AbstractMojo {

    private Log log = getLog();

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {



    }
}
