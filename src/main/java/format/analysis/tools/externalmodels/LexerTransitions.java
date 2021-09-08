package format.analysis.tools.externalmodels;

import java.util.List;
import lombok.Data;

@Data
public class LexerTransitions {
    private String state;
    private List<LexerTransition> transitions;
}
