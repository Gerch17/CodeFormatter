package format.analysis.tools.commands;

import format.analysis.tools.ICommand;
import format.analysis.tools.IContext;

public class Variable implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.appendLexeme(ch);
        context.setTokenName("Variable");
    }
}
