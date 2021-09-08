package format.analysis.tools;

import format.analysis.tools.commands.AppendLexeme;
import format.analysis.tools.commands.AppendPostpone;
import format.analysis.tools.commands.AppendPostponeWithForRename;
import format.analysis.tools.commands.CloseBracket;
import format.analysis.tools.commands.CloseParenthesis;
import format.analysis.tools.commands.EndMultiComment;
import format.analysis.tools.commands.For;
import format.analysis.tools.commands.MultiLineComment;
import format.analysis.tools.commands.NewLine;
import format.analysis.tools.commands.OneLineComment;
import format.analysis.tools.commands.OpenBracket;
import format.analysis.tools.commands.OpenParenthesis;
import format.analysis.tools.commands.Quotes;
import format.analysis.tools.commands.SemiColon;
import format.analysis.tools.commands.Spacing;
import format.analysis.tools.commands.Variable;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {

    private Map<Pair<State, Character>, ICommand> commandMap;

    public CommandRepository() {
        this.commandMap = new HashMap<>();
        commandMap.put(pairBuilder(new State("def"), null), new AppendLexeme());
        commandMap.put(pairBuilder(new State("def"), '\n'), new NewLine());
        commandMap.put(pairBuilder(new State("def"), ';'), new SemiColon());
        commandMap.put(pairBuilder(new State("def"), '}'), new CloseBracket());
        commandMap.put(pairBuilder(new State("def"), '{'), new OpenBracket());
        commandMap.put(pairBuilder(new State("def"), ' '), new Spacing());
        commandMap.put(pairBuilder(new State("def"), '"'), new Quotes());
        commandMap.put(pairBuilder(new State("def"), '('), new OpenParenthesis());
        commandMap.put(pairBuilder(new State("def"), ')'), new CloseParenthesis());
        commandMap.put(pairBuilder(new State("def"), 'f'), new For());
        commandMap.put(pairBuilder(new State("spacing"), ' '), new Spacing());
        commandMap.put(pairBuilder(new State("spacing"), null), new AppendPostpone());
        commandMap.put(pairBuilder(new State("quotes"), null), new Quotes());
        commandMap.put(pairBuilder(new State("still_quotes"), null), new Quotes());
        commandMap.put(pairBuilder(new State("for1"), 'o'), new For());
        commandMap.put(pairBuilder(new State("for1"), null), new AppendPostponeWithForRename());
        commandMap.put(pairBuilder(new State("for2"), 'r'), new For());
        commandMap.put(pairBuilder(new State("for2"), null), new AppendPostponeWithForRename());
        commandMap.put(pairBuilder(new State("for3"), null), new AppendPostpone());
        commandMap.put(pairBuilder(new State("var"), null), new Variable());
        commandMap.put(pairBuilder(new State("one_comment"), '\\'), new OneLineComment());
        commandMap.put(pairBuilder(new State("multi_comment"), '*'), new MultiLineComment());
        commandMap.put(pairBuilder(new State("multi_comment_end"), '/'), new EndMultiComment());
        commandMap.put(pairBuilder(new State("multi_comment_end"), null), new AppendLexeme());

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
