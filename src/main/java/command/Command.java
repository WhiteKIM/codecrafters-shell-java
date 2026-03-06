package command;

public interface Command<T, R> {
    R process(T input);
}
