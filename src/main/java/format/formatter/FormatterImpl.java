package format.formatter;

import exceptions.InvalidCodeException;
import format.analysis.Lexer;
import format.analysis.Token;
import format.analysis.tools.State;
import io.writer.Writer;
import java.io.IOException;

public class FormatterImpl implements Formatter {
    private FormatterCommandRepository commands;
    private FormatterTransition transition;

    public FormatterImpl() {
        commands = new FormatterCommandRepository();
        transition = new FormatterTransition();
    }

    @Override
    public String makeItClear(Lexer lexer, Writer writer) throws IOException, InvalidCodeException {
        State state = new State("not_need");
        int tab = 0;
        while (lexer.hasMoreTokens() && state != null) {
            Token token = lexer.readToken();
            tab = updateTab(tab, token);
            SuperCommand command = commands.getCommand(state, token, tab);
            command.execute(writer);
            state = transition.nextState(token, state);
        }
        return writer.toString();
    }

    private int updateTab(int st, Token token) {
        if (token.getName().equals("{")) {
            st++;
        } else if (token.getName().equals("}")) {
            st--;
        }
        return st;
    }
}
