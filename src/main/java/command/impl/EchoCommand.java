package command.impl;

import command.ShellCommand;

public class EchoCommand implements ShellCommand<String, String> {
    @Override
    public String process(String input) {
        return input;
    }
}
