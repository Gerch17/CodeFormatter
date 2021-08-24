import format.analysis.Lexer;
import format.formatter.FormatterImpl;
import io.reader.Reader;
import io.reader.string.StringReader;
import io.writer.Writer;
import io.writer.string.StringWriter;
import java.io.File;
import java.io.IOException;


public class Application {
    public static void main(String[] args) {
        try {
            File file = new File("D://SberTasks//src//main//resources//Test.txt");
            Reader reader = new StringReader("q{w;e;if(){f;asdasdd;}}");
            Writer writer = new StringWriter();
            FormatterImpl formatterImpl = new FormatterImpl();
            Lexer lexer  = new Lexer(reader);
            System.out.println(formatterImpl.makeItClear(lexer, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
