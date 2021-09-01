package format.analysis.tools;

import format.analysis.tools.commands.AppendLexeme;
import format.analysis.tools.commands.AppendPostpone;
import format.analysis.tools.commands.Brackets;
import format.analysis.tools.commands.CloseBracket;
import format.analysis.tools.commands.ICommand;
import format.analysis.tools.commands.NewLine;
import format.analysis.tools.commands.OpenBracket;
import format.analysis.tools.commands.Quotes;
import format.analysis.tools.commands.SemiColon;
import format.analysis.tools.commands.Spacing;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {

    private Map<Pair<State, Character>, ICommand> commandMap;

    public CommandRepository() {
        this.commandMap = new HashMap<>();
        commandMap.put(pairBuilder(new State("def"), null), new AppendLexeme());
        commandMap.put(pairBuilder(new State("spacing"), null), new AppendPostpone());
        commandMap.put(pairBuilder(new State("def"), '\n'), new NewLine());
        commandMap.put(pairBuilder(new State("def"), ';'), new SemiColon());
        commandMap.put(pairBuilder(new State("def"), '}'), new CloseBracket());
        commandMap.put(pairBuilder(new State("def"), '{'), new OpenBracket());
        commandMap.put(pairBuilder(new State("def"), ' '), new Spacing());
        commandMap.put(pairBuilder(new State("spacing"), ' '), new Spacing());
        commandMap.put(pairBuilder(new State("def"), '"'), new Quotes());
        commandMap.put(pairBuilder(new State("quotes"), null), new Quotes());
        commandMap.put(pairBuilder(new State("still_quotes"), null), new Quotes());
        commandMap.put(pairBuilder(new State("def"), '('), new Brackets());
        commandMap.put(pairBuilder(new State("brackets"), null), new Brackets());
        commandMap.put(pairBuilder(new State("still_brackets"), null), new Brackets());

    }

    private Pair pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);
    }

    public ICommand getCommand(State state, Character ch) {
        ICommand command = commandMap.get(pairBuilder(state, ch));
        if (command == null) {
            command = commandMap.get(pairBuilder(state, null));
        }
        return command;
    }
}
