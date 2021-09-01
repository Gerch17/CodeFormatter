package format.formatter;

import exceptions.InvalidCodeException;
import format.analysis.IToken;
import format.analysis.tools.ILexer;
import format.analysis.tools.State;
import format.formatter.commands.ICommand;
import io.writer.Writer;
import java.io.IOException;

public class FormatterImpl implements Formatter {
    private CommandRepository commands;
    private Transition transition;
    private State state;
    private IContext context;

    public FormatterImpl() {
        commands = new CommandRepository();
        transition = new Transition();
    }

    @Override
    public String format(ILexer lexer, Writer writer) throws IOException, InvalidCodeException {
        context = new Context(writer);
        state = new State("def");
        while (lexer.hasMoreTokens() && !state.getStateName().equals("null")) {
            IToken token = lexer.nextToken();
            ICommand command = commands.getCommand(state, token);
            command.execute(token, context);
            state = transition.nextState(token, state);
        }
        return writer.toString();
    }

}

