package format.analysis.tools.external;

import format.analysis.tools.State;
import format.analysis.tools.externalmodels.LexerTools;
import format.analysis.tools.externalmodels.LexerStateModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@Slf4j
public class ExternalStateTransition {
    private Yaml yaml;
    private Map<Pair<String, String>, String> transitionMap;

    public ExternalStateTransition(String pathToTransition) {
        yaml = new Yaml(new Constructor(LexerStateModel.class));
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
            List<LexerStateModel> lexerTransitions = yaml.load(inputStream);

            for (LexerStateModel transitions : lexerTransitions) {
                for (LexerTools transition : transitions.getTransitions()) {
                    transitionMap.put(new Pair(transitions.getState(), transition.getCh()),
                            String.valueOf(transition.getTransferTo()));
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Cannot find file with such path: {}", pathToTransition);
        }


    }
}
