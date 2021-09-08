package format.analysis.tools;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Context implements IContext {

    private String tokenName;
    private StringBuilder lexeme;
    private StringBuilder postponeBuffer;

    public Context() {
        this.tokenName = "";
        this.lexeme = new StringBuilder();
        this.postponeBuffer = new StringBuilder();
    }

    @Override
    public void appendLexeme(char ch) {
        lexeme.append(ch);
    }

    @Override
    public void setTokenName(String name) {
        tokenName = name;
    }

    @Override
    public void appendPostpone(char ch) {
        postponeBuffer.append(ch);
    }

    @Override
    public StringBuilder getPostponeBuffer() {
        return postponeBuffer;
    }

    @Override
    public Token buildToken() {
        Token token = new Token(tokenName, lexeme.toString());
        log.debug("return token with Name: {} and lexeme: {}", tokenName, lexeme.toString());
        return token;
    }

    @Override
    public void newLexeme() {
        lexeme.setLength(0);
    }

    @Override
    public void setPostponeToZero() {
        postponeBuffer.setLength(0);
    }


}
