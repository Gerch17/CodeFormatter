package format.formatter.commands;

import exceptions.WriterException;
import format.analysis.IToken;
import format.formatter.Context;
import format.formatter.IContext;

public class AppendChar implements ICommand {
    @Override
    public void execute(IToken token, IContext context) throws WriterException {
        context.writeLexeme(token);
    }
}
