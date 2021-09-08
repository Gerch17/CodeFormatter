package format.analysis.tools.commands;

import format.analysis.tools.ICommand;
import format.analysis.tools.IContext;

public class EndMultiComment implements ICommand {
    @Override
    public void execute(char ch, IContext context) {
        context.setTokenName("MultiLineCommentEnd");
        context.appendLexeme(ch);
    }
}
