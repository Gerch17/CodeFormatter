package format.analysis.tools;

import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class Transition {
    private Map<Pair<State, Character>, State> transitionMap;

    public Transition() {
        transitionMap = new HashMap<>();
        transitionMap.put(pairBuilder(new State("def"), null), null);
        transitionMap.put(pairBuilder(new State("def"), ' '), new State("space"));
        transitionMap.put(pairBuilder(new State("space"), ' '), new State("space"));
        transitionMap.put(pairBuilder(new State("space"), null), new State(null));

    }

    public Pair<State, Character> pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);
    }

    public State nextState(State state, Character ch) {
        State newState = transitionMap.get(pairBuilder(state, ch));
        if (newState == null) {
            newState = transitionMap.get(pairBuilder(state, null));
        }
        return newState;
    }
}
