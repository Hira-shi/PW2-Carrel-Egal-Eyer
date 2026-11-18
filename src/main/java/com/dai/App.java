package com.dai;

import com.dai.client.ClientCommand;
import com.dai.server.ServerCommand;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
        name = "app",
        mixinStandardHelpOptions = true,
        version = "1.0",
        description = "Application client-serveur.",
        subcommands = {
                ServerCommand.class,
                ClientCommand.class
        }
)

public class App implements Callable<Integer> {
    @CommandLine.Spec
    private CommandLine.Model.CommandSpec spec;

    @Override
    public Integer call() {
        spec.commandLine().usage(System.err);
        return CommandLine.ExitCode.USAGE;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }}
