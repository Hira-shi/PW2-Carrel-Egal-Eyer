package com.dai.client;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name="client",
        mixinStandardHelpOptions = true,
        description = "Démarre le client"
)

public class ClientCommand implements Callable<Integer> {

    @Override
    public Integer call() {
        // TODO : implémenter le client
        return CommandLine.ExitCode.OK;
    }
}
