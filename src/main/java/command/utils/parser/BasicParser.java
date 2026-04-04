package command.utils.parser;

public class BasicParser implements QuoteParser {
    @Override
    public String process(String input) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < input.length(); i++) {
            char atChar = input.charAt(i);

            if(atChar != ' ') {
                sb.append(atChar);
            } else {
                sb.append(" ");

                while (i + 1 < input.length() && input.charAt(i+1) == ' ') {
                    i++;
                }
            }
        }

        return sb.toString();
    }

    @Override
    public boolean isSupport(String input) {
        return input.charAt(0) != '"' && input.charAt(0) != '\'' ;
    }
}
