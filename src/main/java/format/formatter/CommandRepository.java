package format.formatter;

import format.analysis.IToken;
import format.analysis.tools.State;
import format.formatter.commands.AppendChar;
import format.formatter.commands.AppendWithTab;
import format.formatter.commands.CloseBracket;
import format.formatter.commands.CloseBracketWithoutNewLine;
import format.formatter.commands.DoNothing;
import format.formatter.commands.ICommand;
import format.formatter.commands.OpenBracket;
import format.formatter.commands.Semicolon;
import format.formatter.commands.Spacing;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {
    private Map<Pair<State, String>, ICommand> commandMap;

    public CommandRepository() {
        commandMap = new HashMap<>();
        commandMap.put(pairBuilder(new State("def"), "Char"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "Brackets"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "Quotes"), new AppendChar());
        commandMap.put(pairBuilder(new State("new_line"), null), new AppendWithTab());
        commandMap.put(pairBuilder(new State("new_line"), "NewLine"), new DoNothing());
        commandMap.put(pairBuilder(new State("new_line"), "CloseBracket"), new CloseBracketWithoutNewLine());
        commandMap.put(pairBuilder(new State("def"), "OpenBracket"), new OpenBracket());
        commandMap.put(pairBuilder(new State("def"), "CloseBracket"), new CloseBracket());
        commandMap.put(pairBuilder(new State("def"), "Semicolon"), new Semicolon());
        commandMap.put(pairBuilder(new State("def"), "NewLine"), new DoNothing());
        commandMap.put(pairBuilder(new State("def"), "Spacing"), new Spacing());

    }

    private Pair pairBuilder(State state, String str) {
        return new Pair(state, str);
    }

    public ICommand getCommand(State state, IToken token) {
        ICommand command = commandMap.get(pairBuilder(state, token.getName()));
        if (command == null) {
            command = commandMap.get(pairBuilder(state, null));
        }
        return command;
    }
}
