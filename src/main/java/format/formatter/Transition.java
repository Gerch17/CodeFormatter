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
        transitionMap.put(pairBuilder(new State("new_line"), null), new State("def"));
        transitionMap.put(pairBuilder(new State("new_line"), "CloseBracket"), new State("new_line"));

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
