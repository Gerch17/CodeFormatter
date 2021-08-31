package format.analysis.tools;

import java.util.HashMap;
import java.util.Map;
import org.javatuples.Pair;

public class CommandRepository {

    private Map<Pair<State, Character>, Command> commandMap;

    public CommandRepository() {
        this.commandMap = new HashMap<>();
        commandMap.put(pairBuilder(new State("def"), null), new Command(true));

    }

    private Pair pairBuilder(State state, Character ch) {
        return new Pair<>(state, ch);
    }

    public Command getCommand(State state, Character ch) {
        Command command = commandMap.get(pairBuilder(state, ch));
        if (command == null) {
            command = commandMap.get(pairBuilder(state, null));
            command.setChar(ch);
        }
        return command;
    }
}
