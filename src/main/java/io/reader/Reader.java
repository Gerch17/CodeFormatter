package io.reader;

import exceptions.ReaderException;
import io.IClosable;


public interface Reader extends IClosable {
    char readChar() throws ReaderException;

    boolean hasChar() throws ReaderException;
}
