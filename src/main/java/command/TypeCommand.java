package command;

import core.Interpreter;

import java.io.File;

public class TypeCommand implements Command<String, String> {
    private final String SUPPORT_COMMAND = " is a shell builtin";
    private final String NOT_SUPPORT_COMMAND = ": not found";

    @Override
    public String process(String command) {
        if(Interpreter.isSupportCommand(command)) {
            return command + SUPPORT_COMMAND;
        } else {
            // check Shell Command
            return isShellCommand(command);
        }
    }

    private String isShellCommand(String command) {
        String systemPath = System.getenv("PATH");
        File shellCommand = new File(systemPath, command);

        if(shellCommand.isFile() && shellCommand.canExecute()) {
            return command + "is " + shellCommand.getAbsolutePath();
        }

        return command + NOT_SUPPORT_COMMAND;
    }
}
