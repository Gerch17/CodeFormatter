package format.formatter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode
@ToString
public class FormatterState {
    private int state;

    public FormatterState(int state) {
        this.state = state;
    }
}
