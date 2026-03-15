package command.impl;

import command.BuildInCommand;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class CatCommand implements BuildInCommand<String, String> {
    @Override
    public String process(String input) {
        String[] filePathList = input.split(" ");
        StringBuilder sb = new StringBuilder();

        for(String path : filePathList) {
            File file = new File(path.replaceAll("''", ""));

            if(!file.exists() || !file.isFile()) {
                return null;
            }

            try(BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                sb.append(" ");

            } catch(Exception e) {
                return null;
            }
        }

        return sb.toString();
    }
}
