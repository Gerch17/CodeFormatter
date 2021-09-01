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
        transitionMap.put(pairBuilder(new State("spacing"), null), new State("null"));
        transitionMap.put(pairBuilder(new State("spacing"), ' '), new State("spacing"));
        transitionMap.put(pairBuilder(new State("def"), '\"'), new State("quotes"));
        transitionMap.put(pairBuilder(new State("quotes"), '\"'), new State("null"));
        transitionMap.put(pairBuilder(new State("quotes"), null), new State("quotes"));
        transitionMap.put(pairBuilder(new State("quotes"), '\\'), new State("still_quotes"));
        transitionMap.put(pairBuilder(new State("still_quotes"), null), new State("quotes"));
        transitionMap.put(pairBuilder(new State("def"), '('), new State("brackets"));
        transitionMap.put(pairBuilder(new State("brackets"), null), new State("brackets"));
        transitionMap.put(pairBuilder(new State("brackets"), '('), new State("still_brackets"));
        transitionMap.put(pairBuilder(new State("still_brackets"), ')'), new State("brackets"));
        transitionMap.put(pairBuilder(new State("brackets"), ')'), new State("null"));

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
