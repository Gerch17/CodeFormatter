package io;

import exceptions.CloseException;

public interface IClosable extends AutoCloseable {
    @Override
    void close() throws CloseException;
}
