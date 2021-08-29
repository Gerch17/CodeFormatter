package format.formatter;

import exceptions.WriterException;
import io.writer.Writer;
import java.util.HashMap;
import java.util.Map;

public class SuperCommand {
    private int tabAmount;
    private String method;
    private String append;

    private static final Map<String, ICommand> methods = new HashMap<>() {
        {
            put("closed", new ICommand() {
                @Override
                public void runCommand(Writer writer, int tab, String append) throws WriterException {
                    for (int i = 0; i < append.length(); i++) {
                        writer.writeChar(append.charAt(i));
                    }
                }
            });
            put("default", new ICommand() {
                @Override
                public void runCommand(Writer writer, int tab, String append) throws WriterException {
                    tab--;
                    for (int i = 0; i < append.length(); i++) {
                        writer.writeChar(append.charAt(i));
                    }
                    for (int i = 0; i < tab * 4; i++) {
                        writer.writeChar(' ');
                    }
                }
            });
            put("defaultWithTab", new ICommand() {
                @Override
                public void runCommand(Writer writer, int tab, String append) throws WriterException {
                    tab--;
                    for (int i = 0; i < 4 && tab != -1; i++) {
                        writer.writeChar(' ');
                    }
                    for (int i = 0; i < append.length(); i++) {
                        writer.writeChar(append.charAt(i));
                    }
                    for (int i = 0; i < tab * 4; i++) {
                        writer.writeChar(' ');
                    }
                }
            });
            put("defaultWithTabOpen", new ICommand() {
                @Override
                public void runCommand(Writer writer, int tab, String append) throws WriterException {
                    tab -= 2;
                    for (int i = 0; i < 4 && tab != -1; i++) {
                        writer.writeChar(' ');
                    }
                    for (int i = 0; i < append.length(); i++) {
                        writer.writeChar(append.charAt(i));
                    }
                    tab++;
                    for (int i = 0; i < tab * 4; i++) {
                        writer.writeChar(' ');
                    }
                }
            });
            put("textWithTab", new ICommand() {
                @Override
                public void runCommand(Writer writer, int tab, String append) throws WriterException {
                    append.trim().replaceAll("\\s+", " ").replaceAll("\n", "");
                    for (int i = 0; i < 4; i++) {
                        writer.writeChar(' ');
                    }
                    for (int i = 0; i < append.length(); i++) {
                        writer.writeChar(append.charAt(i));
                    }
                }
            });
            put("text", new ICommand() {
                @Override
                public void runCommand(Writer writer, int tab, String append) throws WriterException {
                    append.trim().replaceAll("\\s+", " ").replaceAll("\n", "");
                    for (int i = 0; i < append.length(); i++) {
                        writer.writeChar(append.charAt(i));
                    }
                }
            });
        }};

    public SuperCommand(String method) {
        this.method = method;
    }

    public SuperCommand setParam(String append, int tab) {
        tabAmount = tab;
        this.append = append;
        return this;
    }

    public int getTabAmount() {
        return tabAmount;
    }

    public void execute(Writer writer) throws WriterException {
        methods.get(method).runCommand(writer, tabAmount, append);
    }
}
