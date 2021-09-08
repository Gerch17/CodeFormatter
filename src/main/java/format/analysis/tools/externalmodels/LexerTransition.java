package format.analysis.tools.externalmodels;

import lombok.Data;

@Data
public class LexerTransition {
    private String ch;
    private String transferTo;
}
