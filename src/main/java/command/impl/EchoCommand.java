package command.impl;

import command.ShellCommand;
import command.utils.handler.ParserHandler;
import command.utils.parser.DoubleQuoteParser;
import command.utils.parser.SingleQuoteParser;

public class EchoCommand implements ShellCommand<String, String> {
    @Override
    public String process(String input) {
        ParserHandler singleQuoteHandler = new ParserHandler(new SingleQuoteParser());
        ParserHandler doubleQuoteHandler = new ParserHandler(new DoubleQuoteParser());

        singleQuoteHandler.setHandler(doubleQuoteHandler);

        return singleQuoteHandler.run(input);
    }
}
