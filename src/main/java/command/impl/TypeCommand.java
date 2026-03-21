package command.impl;

import command.Command;
import core.Interpreter;

import java.io.File;
import java.util.Locale;

public class TypeCommand implements Command<String, String> {
    private final String SUPPORT_COMMAND = " is a shell builtin";
    private final String NOT_SUPPORT_COMMAND = ": not found";

    @Override
    public String process(String command) {
        // 지원 여부 체크
        if(!Interpreter.isSupportCommand(command)) {
            return isShellCommand(command);
        }

        // 여기서는 내장인지 실행가능 쉘커맨드인지 체크
        String systemPath = System.getenv("PATH");
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String[] systemPathList = systemPath.split(File.pathSeparator);

        for(String path : systemPathList) {
            File shellCommand = new File(path, command);

            if(shellCommand.isFile() && shellCommand.canExecute()) {
                return command + " is " + shellCommand.getAbsolutePath();
            }
        }
        
        // 실행가능한 경로 X => 내장
        return command + SUPPORT_COMMAND;
    }

    private String isShellCommand(String command) {
        String systemPath = System.getenv("PATH");
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String[] systemPathList = systemPath.split(File.pathSeparator);

        for(String path : systemPathList) {
            File shellCommand = new File(path, command);

            if(shellCommand.isFile() && shellCommand.canExecute()) {
                return command + " is " + shellCommand.getAbsolutePath();
            }
        }

        return command + NOT_SUPPORT_COMMAND;
    }
}
