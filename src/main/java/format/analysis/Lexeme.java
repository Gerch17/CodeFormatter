package format.analysis;

public class Lexeme {
    private String value;
    private Type type;
    private int tab;

    public Lexeme(Type type, int tab) {
        this.tab = tab;
        this.type = type;
        this.value = "";
    }

    public Lexeme(String value, int tab) {
        this.tab = tab;
        this.type = Type.TEXT;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getTab() {
        return tab;
    }

    public void setTab(int tab) {
        this.tab = tab;
    }
}
