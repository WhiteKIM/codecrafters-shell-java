package command;

public class EchoCommand implements Command<String, String>{
    @Override
    public String process(String input) {
        return "echo: " + input;
    }
}
