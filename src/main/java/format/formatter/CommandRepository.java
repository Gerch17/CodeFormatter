package format.formatter;

import format.analysis.IToken;
import format.analysis.tools.State;
import format.formatter.commands.*;

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
        commandMap.put(pairBuilder(new State("def"), "OpenBracket"), new OpenBracket());
        commandMap.put(pairBuilder(new State("def"), "CloseBracket"), new CloseBracket());
        commandMap.put(pairBuilder(new State("def"), "Semicolon"), new Semicolon());
        commandMap.put(pairBuilder(new State("def"), "NewLine"), new DoNothing());
        commandMap.put(pairBuilder(new State("def"), "Spacing"), new Spacing());
        commandMap.put(pairBuilder(new State("def"), "For"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "Variable"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "OpenParenthesis"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "CloseParenthesis"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "OneLineComment"), new AppendChar());
        commandMap.put(pairBuilder(new State("def"), "MultiLineComment"), new AppendChar());
        commandMap.put(pairBuilder(new State("new_line"), null), new AppendWithTab());
        commandMap.put(pairBuilder(new State("new_line"), "NewLine"), new DoNothing());
        commandMap.put(pairBuilder(new State("new_line"), "CloseBracket"), new CloseBracketWithoutNewLine());
        commandMap.put(pairBuilder(new State("for"), "OpenParenthesis"), new SpaceAndChar());
        commandMap.put(pairBuilder(new State("for"), "Char"), new SpaceAndChar());
        commandMap.put(pairBuilder(new State("for"), "Spacing"), new DoNothing());
        commandMap.put(pairBuilder(new State("for_started"), "Spacing"), new Spacing());
        commandMap.put(pairBuilder(new State("for_started"), "Char"), new AppendChar());
        commandMap.put(pairBuilder(new State("for_started"), "Semicolon"), new AppendChar());
        commandMap.put(pairBuilder(new State("for_started"), "OpenParenthesis"), new AppendChar());
        commandMap.put(pairBuilder(new State("for_started"), "CloseParenthesis"), new AppendChar());
        commandMap.put(pairBuilder(new State("still_for"), "Spacing"), new Spacing());
        commandMap.put(pairBuilder(new State("still_for"), "Char"), new AppendChar());
        commandMap.put(pairBuilder(new State("still_for"), "CloseParenthesis"), new AppendChar());
        commandMap.put(pairBuilder(new State("one_line_comment"), null), new AppendChar());
        commandMap.put(pairBuilder(new State("one_line_comment"), "NewLine"), new AppendChar());
        commandMap.put(pairBuilder(new State("multi_line_comment"), null), new AppendChar());
        commandMap.put(pairBuilder(new State("multi_line_comment"), "MultiLineCommentEnd"), new AppendChar());

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
