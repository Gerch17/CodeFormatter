import format.formatter.FormatterImpl;
import io.reader.Reader;
import io.reader.string.StringReader;
import io.writer.Writer;
import io.writer.string.StringWriter;
import java.io.IOException;


public class Application {
    public static void main(String[] args) {
        try {
            Reader reader = new StringReader("qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;}}");
            Writer writer = new StringWriter();
            FormatterImpl formatterImpl = new FormatterImpl();
            System.out.println(formatterImpl.makeItClear(reader, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
