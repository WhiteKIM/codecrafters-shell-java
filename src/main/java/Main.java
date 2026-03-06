import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;

public class Main {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final Set<String> supportCommandSet = Set.of();

    public static void main(String[] args) throws Exception {
         System.out.print("$ ");
         String commnad = br.readLine();

         if(!supportCommandSet.contains(commnad)) {
             bw.write(commnad + ": " + "command not found");
         }

         bw.flush();
         bw.close();
         br.close();
    }
}
