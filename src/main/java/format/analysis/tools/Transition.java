package format.analysis.tools;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;

@Slf4j
public class Transition {
    private Map<Pair<State, Character>, State> transitionMap;

    public Transition() {
        transitionMap = new HashMap<>();
        transitionMap.put(pairBuilder(new State("def"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("def"), '\n'), new State("null"));
        transitionMap.put(pairBuilder(new State("def"), ' '), new State("spacing"));
        transitionMap.put(pairBuilder(new State("def"), '\"'), new State("quotes"));
        transitionMap.put(pairBuilder(new State("def"), '('), new State("null"));
        transitionMap.put(pairBuilder(new State("def"), ')'), new State("null"));
        transitionMap.put(pairBuilder(new State("def"), 'f'), new State("for1"));
        transitionMap.put(pairBuilder(new State("def"), '\\'), new State("one_comment"));
        transitionMap.put(pairBuilder(new State("def"), '/'), new State("multi_comment"));
        transitionMap.put(pairBuilder(new State("def"), '*'), new State("multi_comment_end"));
        transitionMap.put(pairBuilder(new State("spacing"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("spacing"), ' '), new State("spacing"));
        transitionMap.put(pairBuilder(new State("quotes"), '\"'), new State("null"));
        transitionMap.put(pairBuilder(new State("quotes"), null), new State("quotes"));
        transitionMap.put(pairBuilder(new State("quotes"), '\\'), new State("still_quotes"));
        transitionMap.put(pairBuilder(new State("still_quotes"), null), new State("quotes"));
        transitionMap.put(pairBuilder(new State("for1"), 'o'), new State("for2"));
        transitionMap.put(pairBuilder(new State("for1"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("for2"), 'r'), new State("for3"));
        transitionMap.put(pairBuilder(new State("for2"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("for3"), ' '), new State("null"));
        transitionMap.put(pairBuilder(new State("for3"), '('), new State("null"));
        transitionMap.put(pairBuilder(new State("for3"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("for"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("one_comment"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("multi_comment"), null), new State("null"));
    }

    public Pair<State, Character> pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);
    }

    public State nextState(State state, Character ch) {
        log.debug("nextState by state: {} and char {}", state, ch);
        State newState = transitionMap.get(pairBuilder(state, ch));
        if (newState == null) {
            newState = transitionMap.get(pairBuilder(state, null));
        }
        return newState;
    }
}
