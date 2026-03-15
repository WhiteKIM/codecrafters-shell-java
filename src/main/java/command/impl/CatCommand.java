package command.impl;

import command.BuildInCommand;
import resolver.PathResolver;

import java.io.*;

public class CatCommand implements BuildInCommand<String, String> {
    @Override
    public String process(String input) {
        StringBuilder sb = new StringBuilder();
        ProcessBuilder pcb = new ProcessBuilder("cat " + input);
        pcb.directory(new File(PathResolver.getWorkingDir()));

        System.out.println("cat " + input);

        try {
            Process process = pcb.start();
            InputStream processInputStream = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(processInputStream));

            String line;

            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
            }
        } catch (IOException e) {
            return null;
        }

        return sb.toString();
    }
}
