package format.analysis;

public class Lexeme {
    private String value;
    private Token token;
    private int tab;

    public Lexeme(Token token, int tab) {
        this.tab = tab;
        this.token = token;
    }

    public Lexeme(String value, int tab) {
        this.tab = tab;
        this.token = Token.TEXT;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Token getType() {
        return token;
    }

    public void setType(Token token) {
        this.token = token;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
}
