package command;

public class EchoCommand implements ShellCommand<String, String>{
    @Override
    public String process(String input) {
        return input;
    }
}
