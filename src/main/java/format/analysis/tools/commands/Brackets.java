package format.analysis.tools.commands;

import format.analysis.tools.IContext;

public class Brackets implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.appendLexeme(ch);
        context.setTokenName("Brackets");
    }
}
