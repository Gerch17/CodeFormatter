import exceptions.InvalidCodeException;
import format.formatter.FormatterOldImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.reader.string.StringReader;
import io.writer.string.StringWriter;

import java.io.IOException;


public class TestFormatterOldImpl {

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

    private FormatterOldImpl formatterOldImpl;

    @BeforeEach
    public void before() {
        this.formatterOldImpl = new FormatterOldImpl();
    }

    @Test
    public void simpleTest() throws IOException {
            String actual = formatterOldImpl.makeItClear(new StringReader(SIMPLE_STRING), new StringWriter());
            Assertions.assertEquals(EXPECTED_SIMPLE_STRING, actual);
    }

    @Test
    public void exceptionTest() {
        Assertions.assertThrows(InvalidCodeException.class,
                () -> {
                    formatterOldImpl.makeItClear(new StringReader(EXCEPTION_STRING), new StringWriter());
                });
    }

}
