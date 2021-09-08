package format.analysis.tools.external;

import format.analysis.tools.State;
import format.analysis.tools.external.utils.YamlConstructor;
import format.analysis.tools.externalmodels.LexerTransition;
import format.analysis.tools.externalmodels.LexerTransitions;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.yaml.snakeyaml.Yaml;

@Slf4j
public class ExternalStateTransition {
    private Yaml yaml;
    private Map<Pair<String, String>, String> transitionMap;

    public ExternalStateTransition(String pathToTransition) {
        yaml = new Yaml(new YamlConstructor<>(LexerTransitions.class));
        transitionMap = new HashMap<>();

        initTransition(pathToTransition);
    }

    public State nextState(State state, Character ch) {
        String newState = transitionMap.get(new Pair<>(state.getStateName(), String.valueOf(ch)));
        if (newState == null) {
            newState = transitionMap.get(new Pair<>(state.getStateName(), null));
        }
        return new State(newState);
    }

    private void initTransition(String pathToTransition) {
        try {
            InputStream inputStream = new FileInputStream(pathToTransition);
            List<LexerTransitions> lexerTransitions = yaml.load(inputStream);

            for (LexerTransitions transitions : lexerTransitions) {
                for (LexerTransition transition : transitions.getTransitions()) {
                    transitionMap.put(new Pair(transitions.getState(), transition.getCh()),
                            String.valueOf(transition.getTransferTo()));
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Cannot find file with such path: {}", pathToTransition);
        }


    }
}
