import format.analysis.Lexer;
import format.formatter.FormatterImpl;
import io.reader.string.StringReader;
import io.writer.string.StringWriter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class TestFormatterImpl {

    private static final String SIMPLE_STRING = "qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;}}";
    private static final String EXPECTED_SIMPLE_STRING = "qweqwewqe {\n" +
            "    qweqwe;\n" +
            "    qweqwe;\n" +
            "    if() {\n" +
            "        qweqwe;\n" +
            "        qweqwe;\n" +
            "    }\n" +
            "}\n";
    private static final String ALL_TESTS = "String temp = \"Hello!{};\\\"\";for (int i = 0; i < temp.length(); i++){if (true){int q = 4;}}";
    private static final String EXPECTED_ALL_TESTS = "String temp = \"Hello!{};\\\"\";\n" +
            "for (int i = 0; i < temp.length(); i++) {\n" +
            "    if (true) {\n" +
            "        int q = 4;\n" +
            "    }\n" +
            "}\n";

    private FormatterImpl formatterImpl;

    @BeforeEach
    public void setUp() {
        this.formatterImpl = new FormatterImpl();
    }

    @Test
    public void simpleTest() throws IOException {
            Lexer lexer = new Lexer(new StringReader(SIMPLE_STRING));
            String actual = formatterImpl.makeItClear(lexer, new StringWriter());
            Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

    @Test
    public void allTest() throws IOException {
        Lexer lexer = new Lexer(new StringReader(ALL_TESTS));
        String actual = formatterImpl.makeItClear(lexer, new StringWriter());
        Assertions.assertEquals(EXPECTED_ALL_TESTS, actual);
    }



}
