package format.analysis.tools;

import exceptions.ReaderException;
import format.analysis.IToken;
import format.analysis.tools.external.ExternalCommandRepository;
import format.analysis.tools.external.ExternalStateTransition;
import io.reader.Reader;
import io.reader.string.StringReader;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LexerStateMachine implements ILexer {
    private final Reader reader;
    private IContext context;
    private ExternalCommandRepository commandRepository;
    private ExternalStateTransition transition;
    private static final String PATH_TO_COMMAND = "src\\main\\resources\\LexerCommands.yaml";
    private static final String PATH_TO_TRANSITION = "src\\main\\resources\\LexerTransition.yaml";

    public LexerStateMachine(Reader reader) {
        this.commandRepository = new ExternalCommandRepository(PATH_TO_COMMAND);
        this.transition = new ExternalStateTransition(PATH_TO_TRANSITION);
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
        ICommand command = (ICommand) commandRepository.getCommand(state, Character.valueOf(ch));
        command.execute(ch, context);
        return  transition.nextState(state, ch);
    }
}
