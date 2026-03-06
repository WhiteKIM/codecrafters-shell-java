package command;

public class ExitCommand implements Command<Void, Void> {
    @Override
    public Void process(Void input) {
        return null;
    }
}
