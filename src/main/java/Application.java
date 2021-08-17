import formatter.Formatter;
import reader.Reader;
import reader.string.StringReader;
import writer.Writer;
import writer.string.StringWriter;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        try {
            Reader reader = new StringReader("qweqwewqe{qweqwe;qweqwe;if(){qweqwe;qweqwe;}}");
            Writer writer = new StringWriter();
            Formatter formatter = new Formatter();
            System.out.println(formatter.makeItClear(reader, writer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
