package format.analysis.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {

    private Map<Pair<State, Character>, Command> commandMap;

    public CommandRepository() {
        this.commandMap = new HashMap<>();
        commandMap.put(pairBuilder(new State("def"), '{'), new Command(" {\n"));
        commandMap.put(pairBuilder(new State("def"), '}'), new Command("}\n"));
        commandMap.put(pairBuilder(new State("def"), ';'), new Command(";\n"));
        commandMap.put(pairBuilder(new State("text"), '{'), new Command(" {\n"));
        commandMap.put(pairBuilder(new State("text"), '}'), new Command("}\n"));
        commandMap.put(pairBuilder(new State("text"), ';'), new Command(";\n"));
        commandMap.put(pairBuilder(new State("last"), '{'), new Command(" {\n"));
        commandMap.put(pairBuilder(new State("last"), '}'), new Command("}\n"));
        commandMap.put(pairBuilder(new State("last"), ';'), new Command(";\n"));
        commandMap.put(pairBuilder(new State("for"), ';'), new Command(";"));
        commandMap.put(pairBuilder(new State("for1/2"), ';'), new Command(";"));
        commandMap.put(pairBuilder(new State("for2/2"), ';'), new Command(";"));
    }

    private Pair pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);

    }

    public Command getCommand(State state, Character ch) {
        return commandMap.getOrDefault(pairBuilder(state, ch), new Command(ch.toString()));
    }
}
