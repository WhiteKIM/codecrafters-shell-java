package command;

import core.Interpreter;

public class TypeCommand implements Command<String, String> {
    private final String SUPPORT_COMMAND = " is a shell builtin";
    private final String NOT_SUPPORT_COMMAND = ": not found";

    @Override
    public String process(String input) {
        if(Interpreter.isSupportCommand(input)) {
            return input + SUPPORT_COMMAND;
        } else {
            return input + NOT_SUPPORT_COMMAND;
        }
    }
}
