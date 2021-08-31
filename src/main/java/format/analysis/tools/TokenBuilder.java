package format.analysis.tools;

import format.analysis.Token;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class TokenBuilder {
    private final Map<Pair<State, Character>, String> tokenNameMap;

    public TokenBuilder() {
        tokenNameMap = new HashMap<>();
        tokenNameMap.put(pairBuilder(new State("def"), null), "def");
        tokenNameMap.put(pairBuilder(new State("def"), ' '), "space");
        tokenNameMap.put(pairBuilder(new State("def"), '{'), "open_bracket");
        tokenNameMap.put(pairBuilder(new State("def"), '{'), "close_bracket");
        tokenNameMap.put(pairBuilder(new State("def"), ';'), "semicolon");
        tokenNameMap.put(pairBuilder(new State("def"), '\"'), "literal");
    }

    private Pair pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);
    }

    public Token build(State state, Character ch, Token token) {
        String stateName = tokenNameMap.get(pairBuilder(state, ch));
        if (state == null) {
            stateName = tokenNameMap.get(pairBuilder(state, null));
        }
        token.setName(stateName);
        return token;
    }
}
