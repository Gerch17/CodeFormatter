package format.analysis;

import lombok.Setter;

@Setter
public class Token implements IToken {
    private String name;
    private String lexeme;

    public Token(String name, String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }
}
