package format.analysis.tools.externalmodels;

import java.util.List;
import lombok.Data;

@Data
public class LexerStateModel {
    private String state;
    private List<LexerTools> transitions;
}
