package format.analysis.tools.commands;

import format.analysis.tools.ICommand;
import format.analysis.tools.IContext;

public class AppendPostponeWithForRename implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.appendPostpone(ch);
        context.setTokenName("Char");
    }
}
