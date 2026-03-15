package core;

import command.impl.*;

import java.io.*;
import java.util.Set;
import java.util.stream.Collectors;

public class Interpreter {
    private final BufferedReader br;
    private final BufferedWriter bw;
    private static final Set<String> supportCommandSet = Set.of("exit", "echo", "type", "pwd", "cd", "cat");

    public Interpreter(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;
    }

    public void run() throws Exception {
        System.out.print("$ ");

        // 구분자 처리
        String[] commandLine = br.readLine().split("\\|");

        // 명령어 인자 구분
        String command = commandLine[0].split(" ")[0];

        if(supportCommandSet.contains(command)) {
           builtInCommand(commandLine, command);
        } else {
//            bw.write(command + ": " + Message.NOT_FOUND.getMsg() + "\n");

             checkShellCommand(commandLine, command);
        }

        bw.flush();
    }

    private void builtInCommand(String[] commandLine, String command) throws Exception {
        String args = "";           // 인자값
        String resultMsg = "";      // 결과값

        switch (command) {
            case "exit":
                ExitCommand exit = new ExitCommand();
                exit.process(null);
                doClose();

                break;
            case "echo":
                args = "";

                if(commandLine[0].length() > command.length()) {
                    args = commandLine[0].substring(command.length() + 1);
                }

                EchoCommand echo= new EchoCommand();
                resultMsg = echo.process(args);
                bw.write(resultMsg + "\n");
                break;
            case "type":
                args = "";
                if(commandLine[0].length() > command.length()) {
                    args = commandLine[0].substring(command.length() + 1);
                }

                TypeCommand type = new TypeCommand();
                resultMsg = type.process(args);
                bw.write(resultMsg + "\n");
                break;
            case "pwd" :
                PwdCommand pwd = new PwdCommand();
                resultMsg = pwd.process(null);
                bw.write(resultMsg + "\n");
                break;
            case "cd":
                args = "";

                if(commandLine[0].length() > command.length()) {
                    args = commandLine[0].substring(command.length() + 1);
                }

                CdCommand cd = new CdCommand();
                resultMsg = cd.process(args);

                if(resultMsg != null)
                    bw.write(resultMsg + "\n");
                break;
            case "cat":
                args = "";

                if(commandLine[0].length() > command.length()) {
                    args = commandLine[0].substring(command.length() + 1);
                }

                CatCommand cat = new CatCommand();
                resultMsg = cat.process(args);

                if(resultMsg != null)
                    bw.write(resultMsg + "\n");

                break;
        }
    }

    private void checkShellCommand(String[] commandLine, String command) throws Exception {
        // Check System Path
        String systemPath = System.getenv("PATH");
        String[] pathList = systemPath.split(File.pathSeparator);
        String resultMsg = command + ": " + Message.NOT_FOUND.getMsg();

        for(String path : pathList) {
            File shellCommand = new File(path, command);

            if(shellCommand.isFile() && shellCommand.canExecute()) {
                Process process = Runtime.getRuntime().exec(commandLine[0].split(" "));
                BufferedReader processReader = new BufferedReader(new InputStreamReader(process.getInputStream()));

                bw.write(processReader.lines().collect(Collectors.joining("\n")) + "\n");

                return;
            }
        }

        bw.write(resultMsg + "\n");
    }


    private void doClose() throws IOException {
        br.close();
        bw.close();
        System.exit(0);
    }

    public static boolean isSupportCommand(String command) {
        return supportCommandSet.contains(command);
    }
}
