import exceptions.InvalidCodeException;
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
    private static final String EXCEPTION_STRING = "qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;";

    private FormatterImpl formatterImpl;

    @BeforeEach
    public void before() {
        this.formatterImpl = new FormatterImpl();
    }

    @Test
    public void simpleTest() throws IOException {
            String actual = formatterImpl.makeItClear(new StringReader(SIMPLE_STRING), new StringWriter());
            Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

    @Test
    public void exceptionTest() {
        Assertions.assertThrows(InvalidCodeException.class,
                () -> {
                    formatterImpl.makeItClear(new StringReader(EXCEPTION_STRING), new StringWriter());
                });
    }

}
