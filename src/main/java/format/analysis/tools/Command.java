package format.analysis.tools;

import format.analysis.Token;

public class Command {

    private Boolean append;
    private Character ch;

    public Command(Boolean append) {
        this.append = append;
    }

    public void setChar(Character ch) {
        this.ch = ch;
    }

    public void execute(Token token) {
        if (append) {
            token.setLexeme(token.getLexeme() + ch);
        }
    }
}
