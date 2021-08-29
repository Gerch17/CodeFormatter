package format.formatter;

import format.analysis.Token;
import format.analysis.tools.State;
import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class FormatterCommandRepository {
    private Map<Pair<State, String>, SuperCommand> commandMap;
    private Map<State, SuperCommand> defaultCommandMap;

    public FormatterCommandRepository() {
        commandMap = new HashMap<>();
        commandMap.put(pairBuilder(new State("not_need"), "{"), new SuperCommand("defaultWithTabOpen"));
        commandMap.put(pairBuilder(new State("need"), "{"), new SuperCommand("defaultWithTabOpen"));
        commandMap.put(pairBuilder(new State("not_need"), "}"), new SuperCommand("closed"));
        commandMap.put(pairBuilder(new State("need"), "}"), new SuperCommand("closed"));
        commandMap.put(pairBuilder(new State("not_need"), ";"), new SuperCommand("default"));
        commandMap.put(pairBuilder(new State("need"), ";"), new SuperCommand("defaultWithTab"));

        defaultCommandMap = new HashMap<>();
        defaultCommandMap.put(new State("need"), new SuperCommand("textWithTab"));
        defaultCommandMap.put(new State("not_need"), new SuperCommand("text"));
    }

    private Pair pairBuilder(State state, String str) {
        return new Pair(state, str);
    }

    public SuperCommand getCommand(State state, Token token, int tab) {
        return commandMap.getOrDefault(pairBuilder(state, token.getName()),
                defaultCommandMap.get(state)).setParam(token.getLexeme(), tab);
    }
}
