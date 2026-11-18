package com.dai.server;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name="server",
        mixinStandardHelpOptions = true,
        description = "Le serveur démarre"
)

public class ServerCommand implements Callable<Integer> {

    @Override
    public Integer call(){
        // @Todo Implémenter le serveur
        return CommandLine.ExitCode.OK;
    }
}
