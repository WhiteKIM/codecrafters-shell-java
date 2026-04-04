package command.utils.parser;

public class DoubleQuoteParser implements QuoteParser{
    @Override
    public String process(String input) {
        StringBuilder sb = new StringBuilder();

        boolean isQuote = false;

        for(int i = 0; i < input.length(); i++) {
            char atChar = input.charAt(i);

            if (atChar == '"') {
                isQuote = !isQuote;         // 현재 문자 쌍따옴표
            } else {                        // 현재 문자는 일반 문자열
                if(isQuote) {
                    // 따옴표 내에서는 모든 문자열을 받음
                    sb.append(atChar);
                } else {
                    // 따옴표 밖에서는 다음 공백은 하나만 입력됨

                    if(atChar == ' ') {
                        for(int spaceIndex = i + 1; spaceIndex < input.length(); spaceIndex++) {
                            if(input.charAt(spaceIndex) != ' ') {
                                sb.append(" ");
                                i = spaceIndex;     // 인덱스 위치 변경
                                break;
                            }
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
