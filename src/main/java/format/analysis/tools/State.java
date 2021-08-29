package format.analysis.tools;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class State {
    private String stateName;

    public State(String stateName) {
        this.stateName = stateName;
    }
}
