package command.utils.parser;

import resolver.PathResolver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Set;

public class FileNameParser implements QuoteParser {
    private final Set<String> supportKeyword = Set.of(
            "1>",  ">"
    );

    @Override
    public String process(String input) {
        String[] inputLine = new String[0];

        for(String keyword : supportKeyword) {
            if(input.contains(keyword)) {
                inputLine = input.split(keyword);
                break;
            }
        }

        // 끝에 있으면 파일명
        String fileName = inputLine[inputLine.length - 1];

        File file = new File(PathResolver.getWorkingDir() + "/" + fileName);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedOutputStream br = new BufferedOutputStream(new FileOutputStream(file))) {
            br.write(inputLine[0].getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "";
    }

    @Override
    public boolean isSupport(String input) {
        for(String keyword : supportKeyword) {
            if(input.contains(keyword))
                return true;
        }

        return false;
    }
}
