package command.utils.parser;

public interface QuoteParser {
    String process(String input);
    boolean isSupport(String input);        // 지원여부
}
