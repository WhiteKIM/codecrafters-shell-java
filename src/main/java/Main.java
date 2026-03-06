import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;

public class Main {
    private static final Interpreter interpreter = new Interpreter();

    public static void main(String[] args) throws Exception {
        while (true) {
            interpreter.run();
        }
    }
}
