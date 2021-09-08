package format.analysis.tools.commands;

import format.analysis.tools.ICommand;
import format.analysis.tools.IContext;

public class Quotes implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.setTokenName("Quotes");
        context.appendLexeme(ch);
    }
}
