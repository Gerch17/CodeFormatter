package format.analysis;

import exceptions.ReaderException;
import format.analysis.tools.Command;
import format.analysis.tools.CommandRepository;
import format.analysis.tools.State;
import format.analysis.tools.Transition;
import io.reader.Reader;

public class Lexer implements ILexer {
    private final Reader reader;
    private StringBuilder sb;
    private CommandRepository commands;
    private Transition transitions;


    public Lexer(Reader reader) {
        this.reader = reader;
        sb = new StringBuilder();
        commands = new CommandRepository();
        transitions = new Transition();
    }

    @Override
    public Token readToken() throws ReaderException {
        State state = new State("def");
        Token token = new Token(state.getStateName(), "");
        while (reader.hasChar() && state != null) {
            char ch = reader.readChar();
            Command command = commands.getCommand(state, ch);
            command.execute(token);
            token.setName(String.valueOf(ch));
            state = transitions.nextState(state, ch);
        }
        return token;
    }


    @Override
    public boolean hasMoreTokens() throws ReaderException {
        if (reader.hasChar()) {
            return true;
        }
        return false;
    }
}
