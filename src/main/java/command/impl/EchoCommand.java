package command.impl;

import command.ShellCommand;
import command.utils.handler.ParserHandler;
import command.utils.parser.BackSlashParser;
import command.utils.parser.BasicParser;
import command.utils.parser.DoubleQuoteParser;
import command.utils.parser.SingleQuoteParser;

public class EchoCommand implements ShellCommand<String, String> {
    @Override
    public String process(String input) {
        ParserHandler basicHandler = new ParserHandler(new BasicParser());
        ParserHandler backslashHandler = new ParserHandler(new BackSlashParser());
        ParserHandler singleQuoteHandler = new ParserHandler(new SingleQuoteParser());
        ParserHandler doubleQuoteHandler = new ParserHandler(new DoubleQuoteParser());

        singleQuoteHandler.setHandler(doubleQuoteHandler);
        doubleQuoteHandler.setHandler(backslashHandler);
        backslashHandler.setHandler(basicHandler);

        return singleQuoteHandler.run(input);
    }
}
