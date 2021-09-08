package format.analysis.tools.externalmodels;

import lombok.Data;
import java.util.List;

@Data
public class LexerTransitions {
    private String state;
    private List<LexerTransition> transitions;
}
