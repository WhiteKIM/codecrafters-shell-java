package command.utils.handler;

import command.utils.parser.QuoteParser;

public class ParserHandler {
    private ParserHandler handler;
    private final QuoteParser parser;

    public ParserHandler(QuoteParser parser) {
        this.parser = parser;
    }

    public void setHandler(ParserHandler handler) {
        this.handler = handler;
    }

    public String run(String input) {
        if(parser.isSupport(input)) {
            return process(input);
        }

        return handler.run(input);
    }

    public String process(String input) {
        return parser.process(input);
    }
}
