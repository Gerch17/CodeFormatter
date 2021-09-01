package format.formatter.commands;

import exceptions.WriterException;
import format.analysis.IToken;
import format.formatter.IContext;

public class NewLine implements ICommand {
    @Override
    public void execute(IToken token, IContext context) throws WriterException {
        context.writeNewLine();
    }
}
