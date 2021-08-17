package reader.string;

import reader.Reader;

public class StringReader implements Reader {
    char[] content;
    int index;

    public StringReader(String content) {
        this.content = content.toCharArray();
    }


    @Override
    public char readChar() {
        return content[index++];
    }

    @Override
    public boolean hasChar() {
        return index < content.length;
    }

    @Override
    public void close() throws Exception {

    }
}
