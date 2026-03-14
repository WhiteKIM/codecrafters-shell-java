package command.impl;

import command.BuildInCommand;
import resolver.PathResolver;
import utils.NotExistPathException;

public class CdCommand implements BuildInCommand<String, String> {
    @Override
    public String process(String input) {
        try {
            PathResolver.moveWorkingDir(input);
        } catch (NotExistPathException e) {
            return "cd " + input + ": No such file or directory";
        }

        return null;
    }
}
