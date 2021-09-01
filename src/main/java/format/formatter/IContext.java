package format.formatter;

import exceptions.WriterException;
import format.analysis.IToken;

public interface IContext {
    void writeLexeme(IToken token) throws WriterException;

    void writeNewLine() throws WriterException;

    void writeIndent() throws WriterException;

    void writeSpace() throws WriterException;

    void incrementTab();

    void decrementTab();


}
