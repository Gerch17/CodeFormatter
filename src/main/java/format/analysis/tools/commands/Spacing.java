package format.analysis.tools.commands;

import format.analysis.tools.IContext;

public class Spacing implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.setTokenName("Spacing");
        context.appendLexeme(ch);
    }
}
