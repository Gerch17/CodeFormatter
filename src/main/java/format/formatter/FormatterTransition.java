package format.formatter;

import format.analysis.Token;
import format.analysis.tools.State;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class FormatterTransition {
    private Map<Pair<State, String>, State> transitionMap;
    private Map<State, State> defaultTransitionMap;

    public FormatterTransition() {
        transitionMap = new HashMap<>();
        transitionMap.put(pairBuilder(new State("not_need"), "{"), new State("need"));
        transitionMap.put(pairBuilder(new State("need"), "{"), new State("need"));
        transitionMap.put(pairBuilder(new State("not_need"), ";"), new State("need"));
        transitionMap.put(pairBuilder(new State("need"), ";"), new State("need"));
        transitionMap.put(pairBuilder(new State("not_need"), "}"), new State("not_need"));

        defaultTransitionMap = new HashMap<>();
        defaultTransitionMap.put(new State("need"), new State("not_need"));
        defaultTransitionMap.put(new State("not_need"), new State("not_need"));
    }

    private Pair pairBuilder(State state, String character) {
        return new Pair(state, character);
    }

    public State nextState(Token token, State state) {
        return transitionMap.getOrDefault(pairBuilder(state, token.getName()), defaultTransitionMap.get(state));
    }
}
