package command.impl;

import command.BuildInCommand;
import resolver.PathResolver;

public class PwdCommand implements BuildInCommand<Void, String> {
    @Override
    public String process(Void input) {
        return PathResolver.getWorkingDir();
    }
}
