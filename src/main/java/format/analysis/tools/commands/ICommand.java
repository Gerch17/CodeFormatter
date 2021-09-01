package format.analysis.tools.commands;

import format.analysis.tools.IContext;

public interface ICommand {
    void execute(char ch, IContext context);
}
