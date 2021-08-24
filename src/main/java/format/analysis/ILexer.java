package format.analysis;

import exceptions.ReaderException;

public interface ILexer {
    boolean hasMoreTokens() throws ReaderException;

    IToken readToken() throws ReaderException;
}
