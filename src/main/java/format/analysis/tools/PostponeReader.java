package format.analysis.tools;

public class PostponeReader {
    private Character ch;

    public Boolean hasMoreChars() {
        return ch != null;
    }

    public void setChar(Character ch) {
        this.ch = ch;
    }

    public Character getCh() {
        Character temp = ch;
        ch = null;
        return temp;
    }
}
