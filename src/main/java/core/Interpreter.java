package core;

import command.EchoCommand;
import command.ExitCommand;
import command.TypeCommand;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Set;

public class Interpreter {
    private final BufferedReader br;
    private final BufferedWriter bw;
    private static final Set<String> supportCommandSet = Set.of("exit", "echo", "type");

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
        String args = "";

        if(!supportCommandSet.contains(command)) {
            bw.write(command + ": " + Message.NOT_FOUND.getMsg() + "\n");
        }

        String resultMsg  = "";

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
        }

        bw.flush();
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
