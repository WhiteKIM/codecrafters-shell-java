package core;

import command.impl.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Set;

public class Interpreter {
    private final BufferedReader br;
    private final BufferedWriter bw;
    private static final Set<String> supportCommandSet = Set.of("exit", "echo", "type", "pwd", "cd");

    public Interpreter(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;
    }

    public void run() throws Exception {
        System.out.print("$ ");

        // 구분자 처리
        String[] commandLine = br.readLine().split("\\|");
        
        // 공백 제거
        for(int i = 0; i < commandLine.length; i++) {
            commandLine[i] = commandLine[i].trim();
        }

        // 명령어 인자 구분
//        String command = commandLine[0].split(" ")[0];
        String command = commandLine[0].trim().split("\\s+")[0].replaceAll("^['\"]|['\"]$", "");

        if(supportCommandSet.contains(command)) {
           builtInCommand(commandLine, command);
        } else {
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
                System.out.println(commandLine[0]);
                ProcessBuilder pcb = new ProcessBuilder("bash", "-c", commandLine[0]);
                pcb.inheritIO();
                Process process = pcb.start();
                process.getInputStream().transferTo(System.out);
                process.getErrorStream().transferTo(System.err);
//                args = "";
//
//                if(commandLine[0].length() > command.length()) {
//                    args = commandLine[0].substring(command.length() + 1);
//                }
//
//                CatCommand cat = new CatCommand();
//                resultMsg = cat.process(args);
//
//                if(resultMsg != null)
//                    bw.write(resultMsg + "\n");

                break;
        }
    }

    private void checkShellCommand(String[] commandLine, String command) throws Exception {
        String systemPath = System.getenv("PATH");
        String os = System.getProperty("os.name").toLowerCase(Locale.ROOT);
        String[] systemPathList = systemPath.split(File.pathSeparator);

        for(String path : systemPathList) {
            File shellCommand = new File(path, command);

            if(shellCommand.isFile() && shellCommand.canExecute()) {
                // 여기까지 오면 실행가능한 명령어임
                ProcessBuilder pcb = new ProcessBuilder("bash", "-c", commandLine[0]);
                pcb.inheritIO();
                Process process = pcb.start();
                process.waitFor();
                return;
            }
        }

        String msg = command + ": command not found";

        bw.write(msg + "\n");
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
