package format.analysis.tools;

public interface IContext {
    void appendLexeme(char ch);

    void setTokenName(String name);

    void appendPostpone(char ch);

    StringBuilder getPostponeBuffer();

    Token buildToken();

    void newLexeme();

    void setPostponeToZero();
}
