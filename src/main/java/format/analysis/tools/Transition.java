package format.analysis.tools;

import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class Transition {
    private Map<Pair<State, Character>, State> transitionMap;
    private Map<State, State> defaultTransitionMap;

    public Transition() {
        transitionMap = new HashMap<>();
        transitionMap.put(pairBuilder(new State("def"), '{'), null);
        transitionMap.put(pairBuilder(new State("def"), '}'), null);
        transitionMap.put(pairBuilder(new State("def"), ';'), null);
        transitionMap.put(pairBuilder(new State("def"), '\"'), new State("quotes"));
        transitionMap.put(pairBuilder(new State("quotes"), '\"'), new State("text"));
        transitionMap.put(pairBuilder(new State("quotes"), '\\'), new State("slash_quotes"));
        transitionMap.put(pairBuilder(new State("slash_quotes"), '\"'), new State("quotes"));
        transitionMap.put(pairBuilder(new State("text"), '}'), new State("def"));
        transitionMap.put(pairBuilder(new State("text"), '{'), new State("def"));
        transitionMap.put(pairBuilder(new State("text"), ' '), null);
        transitionMap.put(pairBuilder(new State("text"), '{'), null);
        transitionMap.put(pairBuilder(new State("text"), '}'), null);
        transitionMap.put(pairBuilder(new State("text"), ';'), null);
        transitionMap.put(pairBuilder(new State("def"), 'f'), new State("for1"));
        transitionMap.put(pairBuilder(new State("text"), 'f'), new State("for1"));
        transitionMap.put(pairBuilder(new State("for1"), 'o'), new State("for2"));
        transitionMap.put(pairBuilder(new State("for2"), 'r'), new State("for3"));
        transitionMap.put(pairBuilder(new State("for3"), ' '), new State("for3"));
        transitionMap.put(pairBuilder(new State("for3"), '('), new State("for"));
        transitionMap.put(pairBuilder(new State("for"), ';'), new State("for1/2"));
        transitionMap.put(pairBuilder(new State("for1/2"), ';'), new State("for2/2"));
        transitionMap.put(pairBuilder(new State("for1/2"), '('), new State("notfor1/2"));
        transitionMap.put(pairBuilder(new State("notfor1/2"), ')'), new State("for1/2"));
        transitionMap.put(pairBuilder(new State("for2/2"), '('), new State("notfor2/2"));
        transitionMap.put(pairBuilder(new State("notfor2/2"), ')'), new State("for2/2"));
        transitionMap.put(pairBuilder(new State("for2/2"), ')'), new State("text"));
        transitionMap.put(pairBuilder(new State("for"), ':'), new State("foreach"));
        transitionMap.put(pairBuilder(new State("foreach"), '('), new State("notforeach"));
        transitionMap.put(pairBuilder(new State("notforeach"), ')'), new State("foreach"));
        transitionMap.put(pairBuilder(new State("foreach"), ')'), new State("text"));

        defaultTransitionMap = new HashMap<>();
        defaultTransitionMap.put(stateBuilder("def"), stateBuilder("text"));
        defaultTransitionMap.put(stateBuilder("slash_quotes"), stateBuilder("quotes"));
        defaultTransitionMap.put(stateBuilder("quotes"), stateBuilder("quotes"));
        defaultTransitionMap.put(stateBuilder("text"), stateBuilder("text"));
        defaultTransitionMap.put(stateBuilder("last"), null);
        defaultTransitionMap.put(stateBuilder("for1"), stateBuilder("text"));
        defaultTransitionMap.put(stateBuilder("for2"), stateBuilder("text"));
        defaultTransitionMap.put(stateBuilder("for3"), stateBuilder("text"));
        defaultTransitionMap.put(stateBuilder("for"), stateBuilder("for"));
        defaultTransitionMap.put(stateBuilder("foreach"), stateBuilder("foreach"));
        defaultTransitionMap.put(stateBuilder("notforeach"), stateBuilder("notforeach"));
        defaultTransitionMap.put(stateBuilder("for1/2"), stateBuilder("for1/2"));
        defaultTransitionMap.put(stateBuilder("notfor1/2"), stateBuilder("notfor1/2"));
        defaultTransitionMap.put(stateBuilder("for2/2"), stateBuilder("for2/2"));
        defaultTransitionMap.put(stateBuilder("notfor2/2"), stateBuilder("notfor2/2"));
    }

    public State stateBuilder(String stateName) {
        return new State(stateName);
    }

    public Pair pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);
    }

    public State nextState(State state, Character ch) {
        return transitionMap.getOrDefault(pairBuilder(state, ch), defaultTransitionMap.get(state));
    }
}
