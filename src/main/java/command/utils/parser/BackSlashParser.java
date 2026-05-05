package command.utils.parser;

import java.util.Set;

public class BackSlashParser implements QuoteParser {
    private final Set<Character> specialWord = Set.of(
                    '\'', '\\', '\"', '$', '#', '?', '.', ',', '|',
                    '&', '*', '(', ')'
    );

    // CASE
    // backslash space between

    // backslash combine other space

    // backslash with special word

    @Override
    public String process(String input) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length(); i++) {
            char atChar = input.charAt(i);

            if(atChar != '\\') {
                sb.append(atChar);
            } else {
                if(i + 1 < input.length()) {
                    char nextChar = input.charAt(i + 1);

                    // 특수 문자
                    if(specialWord.contains(nextChar)) {
                        sb.append(nextChar);
                        i += 1;
                    } else if(nextChar == ' ') {// 공백
                        for(int j = i + 1; j < input.length(); j++) {
                            if(input.charAt(j) != ' ') {
                                sb.append(' ');
                                i = j;  // 위치 갱신
                                break;
                            } 
                        }
                    } else {
                        // 다음 문자는 일반 문자열
                        continue;
                    }
                }
            }
        }

        return sb.toString();
    }

    @Override
    public boolean isSupport(String input) {
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '\\') {
                return true;
            }
        }

        return false;
    }
}
