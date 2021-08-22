import format.formatter.FormatterImpl;
import io.reader.Reader;
import io.reader.file.FileReader;
import io.reader.string.StringReader;
import io.writer.Writer;
import io.writer.file.FileWriter;
import io.writer.string.StringWriter;
import java.io.File;
import java.io.IOException;


public class Application {
    public static void main(String[] args) {
        try {
            File file = new File("D://SberTasks//src//main//resources//Test.txt");
            Reader reader = new StringReader("qweqweqweqw{qweqweqw;qweqwe;if(){sadasdasdf;asdasdd;}}");
            Writer writer = new StringWriter();
            FormatterImpl formatterImpl = new FormatterImpl();
            System.out.println(formatterImpl.makeItClear(reader, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
