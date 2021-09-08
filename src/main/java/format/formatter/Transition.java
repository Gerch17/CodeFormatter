package format.formatter;

import format.analysis.IToken;
import format.analysis.tools.State;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

@Slf4j
public class Transition {
    private Map<Pair<State, String>, State> transitionMap;

    public Transition() {
        transitionMap = new HashMap<>();
        transitionMap.put(pairBuilder(new State("def"), "Char"), new State("def"));
        transitionMap.put(pairBuilder(new State("def"), null), new State("def"));
        transitionMap.put(pairBuilder(new State("def"), "Semicolon"), new State("new_line"));
        transitionMap.put(pairBuilder(new State("def"), "OpenBracket"), new State("new_line"));
        transitionMap.put(pairBuilder(new State("def"), "CloseBracket"), new State("new_line"));
        transitionMap.put(pairBuilder(new State("def"), "Variable"), new State("def"));
        transitionMap.put(pairBuilder(new State("def"), "OpenParenthesis"), new State("def"));
        transitionMap.put(pairBuilder(new State("def"), "CloseParenthesis"), new State("def"));
        transitionMap.put(pairBuilder(new State("def"), "For"), new State("for"));
        transitionMap.put(pairBuilder(new State("def"), "OneLineComment"), new State("one_line_comment"));
        transitionMap.put(pairBuilder(new State("def"), "MultiLineComment"), new State("multi_line_comment"));
        transitionMap.put(pairBuilder(new State("new_line"), null), new State("def"));
        transitionMap.put(pairBuilder(new State("new_line"), "OneLineComment"), new State("one_line_comment"));
        transitionMap.put(pairBuilder(new State("new_line"), "MultiLineComment"), new State("multi_line_comment"));
        transitionMap.put(pairBuilder(new State("new_line"), "CloseBracket"), new State("new_line"));
        transitionMap.put(pairBuilder(new State("new_line"), "For"), new State("for"));
        transitionMap.put(pairBuilder(new State("still_for"), "CloseParenthesis"), new State("for_started"));
        transitionMap.put(pairBuilder(new State("for"), "OpenParenthesis"), new State("for_started"));
        transitionMap.put(pairBuilder(new State("for_started"), "OpenParenthesis"), new State("still_for"));
        transitionMap.put(pairBuilder(new State("for_started"), "CloseParenthesis"), new State("def"));
        transitionMap.put(pairBuilder(new State("for_started"), null), new State("for_started"));
        transitionMap.put(pairBuilder(new State("one_line_comment"), "NewLine"), new State("def"));
        transitionMap.put(pairBuilder(new State("one_line_comment"), null), new State("one_line_comment"));
        transitionMap.put(pairBuilder(new State("multi_line_comment"), null), new State("multi_line_comment"));
        transitionMap.put(pairBuilder(new State("multi_line_comment"), "MultiLineCommentEnd"), new State("def"));
    }

    private Pair pairBuilder(State state, String character) {
        return new Pair(state, character);
    }

    public State nextState(IToken token, State state) {
        log.debug("nextState by state: {} and tokenName: {} and tokenLexeme: {}",
                state.getStateName(),
                token.getName(),
                token.getLexeme());
        State nextState = transitionMap.get(pairBuilder(state, token.getName()));
        if (nextState == null) {
            nextState = transitionMap.get(pairBuilder(state, null));
        }
        return nextState;
    }
}
