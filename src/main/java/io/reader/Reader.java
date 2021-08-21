package io.reader;

import exceptions.ReaderException;
import io.IClosable;
import java.io.IOException;


public interface Reader extends IClosable {
    char readChar() throws ReaderException;

    boolean hasChar() throws ReaderException;
}
