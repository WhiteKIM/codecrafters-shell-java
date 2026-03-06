import command.Command;
import command.ExitCommand;

import java.io.*;
import java.util.Set;

public class Interpreter {
    private final BufferedReader br;
    private final BufferedWriter bw;
    private final Set<String> supportCommandSet = Set.of("exit");

    public Interpreter(BufferedReader br, BufferedWriter bw) {
        this.br = br;
        this.bw = bw;
    }

    public void run() throws Exception {
        System.out.print("$ ");

        String command = br.readLine();

        if(!supportCommandSet.contains(command)) {
            bw.write(command + ": " + Message.NOT_FOUND.getMsg() + "\n");
        }

        switch (command) {
            case "exit":
                Command exit = new ExitCommand();
                exit.process();
                doClose();

                break;
        }

        bw.flush();
    }

    private void doClose() throws IOException {
        br.close();
        bw.close();
        System.exit(0);
    }
}
