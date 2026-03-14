package command.impl;

import command.BuildInCommand;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PwdCommnad implements BuildInCommand<Void, String> {
    @Override
    public String process(Void input) {
        Path currentPath = Paths.get("");
        return currentPath.toAbsolutePath().toString();
    }
}
