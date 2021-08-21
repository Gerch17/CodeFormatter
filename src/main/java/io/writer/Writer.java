package io.writer;

import exceptions.WriterException;
import io.IClosable;
import java.io.IOException;


public interface Writer extends IClosable {
    void writeChar(char ch) throws WriterException;
}
