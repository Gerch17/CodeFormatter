package format.analysis.tools.commands;

import format.analysis.tools.IContext;

public class CloseBracket implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.setTokenName("CloseBracket");
        context.appendLexeme(ch);
    }
}
