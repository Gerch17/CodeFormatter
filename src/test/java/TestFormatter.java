import exceptions.InvalidCodeException;
import formatter.Formatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reader.Reader;
import reader.string.StringReader;
import writer.string.StringWriter;

import java.io.IOException;


public class TestFormatter {

    private static final String SIMPLE_STRING = "qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;}}";
    private static final String EXPECTED_SIMPLE_STRING = "qweqwewqe{\n" +
            "    qweqwe;\n" +
            "    qweqwe;\n" +
            "    if(){\n" +
            "        qweqwe;\n" +
            "        qweqwe;\n" +
            "    }\n" +
            "}";
    private static final String EXCEPTION_STRING = "qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;";

    private Formatter formatter;

    @BeforeEach
    public void before() {
        this.formatter = new Formatter();
    }

    @Test
    public void simpleTest() throws IOException {
            String actual = formatter.makeItClear(new StringReader(SIMPLE_STRING), new StringWriter());
            Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

    @Test
    public void exceptionTest() {
        Assertions.assertThrows(InvalidCodeException.class,
                () -> {
                    formatter.makeItClear(new StringReader(EXCEPTION_STRING), new StringWriter());
                });
    }

}
