package format.analysis.tools;

import exceptions.ReaderException;
import format.analysis.IToken;
import format.analysis.tools.commands.ICommand;
import io.reader.Reader;
import io.reader.string.StringReader;

public class LexerStateMachine implements ILexer {
    private final CommandRepository commandRepository;
    private final Transition transition;
    private final Reader reader;
    private IContext context;

    public LexerStateMachine(Reader reader) {
        this.commandRepository = new CommandRepository();
        this.transition = new Transition();
        this.reader = reader;
        this.context = new Context();
    }

    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasChar();
    }

    @Override
    public IToken nextToken() throws ReaderException {
        context.newLexeme();
        State state = new State("def");

        Reader postponeReader = new StringReader(context.getPostponeBuffer().toString());

        while (postponeReader.hasChar() && !state.getStateName().equals("null")) {
            state = step(postponeReader, state, context);
        }

        context.setPostponeToZero();

        while (reader.hasChar() && !state.getStateName().equals("null")) {
            state = step(reader, state, context);
        }
        return context.buildToken();
    }

    private State step(Reader reader, State state, IContext context) throws ReaderException {
        char ch = reader.readChar();
        ICommand command = commandRepository.getCommand(state, ch);
        command.execute(ch, context);
        return  transition.nextState(state, ch);
    }
}
