import format.analysis.tools.ILexer;
import format.analysis.tools.LexerStateMachine;
import format.formatter.FormatterImpl;
import io.reader.Reader;
import io.reader.string.StringReader;
import io.writer.Writer;
import io.writer.string.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class FormatterTest {
    private static final String TEST_STRING = "qwe;qwe;if(){qwe;if(){for(int i = 0 ; i < temp.length(); i++){String             amogus = \"{};\\\"\";}}}";
    private static final String EXPECTED_TEST_STRING = "qwe;\n" +
            "qwe;\n" +
            "if(){\n" +
            "    qwe;\n" +
            "    if(){\n" +
            "        for(int i = 0 ; i < temp.length(); i++){\n" +
            "            String amogus = \"{};\\\"\";\n" +
            "        }\n" +
            "    }\n" +
            "}\n";
    private Writer writer;
    private Reader reader;
    private ILexer lexer;


    @BeforeEach
    public void setUp() {
        reader = new StringReader(TEST_STRING);
        lexer = new LexerStateMachine(reader);
        writer = new StringWriter();
    }

    @Test
    public void test() throws IOException {
        FormatterImpl formatter = new FormatterImpl();

        Assertions.assertEquals(EXPECTED_TEST_STRING, formatter.format(lexer, writer));
    }
}
