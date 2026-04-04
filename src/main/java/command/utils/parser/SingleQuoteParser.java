package command.utils.parser;

public class SingleQuoteParser implements QuoteParser {
    @Override
    public String process(String input) {
        StringBuilder sb = new StringBuilder();

        boolean isQuote = false;

        input = input.replaceAll("''", "");

        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '\'' && !isQuote) {       // 여는 따옴표
                isQuote = true;
                continue;
            } else if(input.charAt(i) == '\'' && isQuote) { // 닫는 따옴표
                isQuote = false;
                continue;
            }

            if(isQuote) {
                sb.append(input.charAt(i));
            } else {
                if(input.charAt(i) == ' ') {
                    if(i + 1 < input.length() && input.charAt(i+1) != ' ') {
                        sb.append(' ');
                    }
                } else {
                    sb.append(input.charAt(i));
                }
            }
        }

        return sb.toString();
    }

    @Override
    public boolean isSupport(String input) {
        return input.charAt(0) == '\'';
    }
}
