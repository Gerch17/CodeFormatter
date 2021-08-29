package format.analysis.tools;

import format.analysis.Token;

public class Command {

    private String append;

    public Command(String append) {
        this.append = append;
    }

    public void execute(Token token) {
        token.setLexeme(token.getLexeme() + append);
    }
}
