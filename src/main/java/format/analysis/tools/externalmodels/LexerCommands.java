package format.analysis.tools.externalmodels;

import lombok.Data;
import java.util.List;

@Data
public class LexerCommands {
    private String state;
    private List<LexerCommand> commands;
}
