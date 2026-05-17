package command.utils.parser;

import java.util.Set;

public class DoubleQuoteParser implements QuoteParser{
    private final Set<Character> specialWord = Set.of(
            '\'', '\\', '\"', '$', '#', '?', '.', ',', '|',
            '&', '*', '(', ')'
    );

    @Override
    public String process(String input) {
        StringBuilder sb = new StringBuilder();

        boolean isQuote = false;

        for(int i = 0; i < input.length(); i++) {
            char atChar = input.charAt(i);

            if (atChar == '\\') {
                // 특수문자 치환 추가
                if (i + 1 < input.length()) {
                    char nextChar = input.charAt(i + 1);

                    // 특수 문자
                    if (specialWord.contains(nextChar)) {
                        sb.append(nextChar);
                        i += 1; // 특수문자 위치까지 벗어나야 함
                        continue;
                    }
                }
            }

            if (atChar == '"') {
                isQuote = !isQuote;         // 현재 문자 쌍따옴표
            } else {                        // 현재 문자는 일반 문자열
                if(isQuote && atChar != '\\') {
                    // 따옴표 내에서는 모든 문자열을 받음
                    sb.append(atChar);
                } else {
                    // 따옴표 밖에서는 다음 공백은 하나만 입력됨
                    if(atChar == ' ') {
                        sb.append(" ");

                        while (i + 1 < input.length() &&  input.charAt(i + 1) == ' ') {
                            i++;
                        }

                        continue;
                    }

                    sb.append(atChar);
                }
            }
        }

        return sb.toString();
    }
    
    /**
     * 지원여부 확인
     * @param input - 입력된 문자열
     * @return
     */
    @Override
    public boolean isSupport(String input) {
        return input.charAt(0) == '"';
    }
}
