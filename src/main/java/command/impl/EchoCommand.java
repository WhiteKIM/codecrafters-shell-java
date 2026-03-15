package command.impl;

import command.ShellCommand;

public class EchoCommand implements ShellCommand<String, String> {
    @Override
    public String process(String input) {
        return quoteProcess(input);
    }

    private String quoteProcess(String input) {
        StringBuilder sb = new StringBuilder();

        int begin = -1;
        int end = -1;
        int blank = 0;

        input = input.replaceAll("''", "");

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '\'') {
                if(begin == -1) {
                    begin = i;
                } else {
                    end = i;
                }
            } else {
                if(input.charAt(i) != ' ') {
                    if(blank != 0) {
                        if(begin != -1 && end != -1) {
                            sb.append(" ".repeat(Math.max(0, end - begin + 1)));

                            // 쌍따옴표 내 문자열을 정상적으로 입력
                            begin = -1;
                            end = -1;
                        } else {
                            sb.append(" ");
                            blank = 0;
                        }
                    }

                    sb.append(input.charAt(i));
                } else {
                    blank += 1;
                }
            }




        }

        return sb.toString();
    }
}
