import java.io.*;
import java.util.Set;

public class Interpreter {
    private final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final Set<String> supportCommandSet = Set.of();

    public void run() throws IOException {
        System.out.print("$ ");

        String command = br.readLine();

        if(!supportCommandSet.contains(command)) {
            bw.write(command + ": " + Message.NOT_FOUND.getMsg() + "\n");
        }



        bw.flush();
    }

    /**
     * 인터프리터 종료
     */
    public void exit() throws Exception {
        br.close();
        bw.close();
    }
}
