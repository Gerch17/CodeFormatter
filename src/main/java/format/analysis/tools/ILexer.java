package format.analysis.tools;

import exceptions.ReaderException;
import format.analysis.IToken;

public interface ILexer {
    boolean hasMoreTokens() throws ReaderException;

    IToken nextToken() throws ReaderException;
}
