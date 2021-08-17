package formatter;

import exceptions.InvalidCodeException;
import org.apache.commons.lang3.StringUtils;
import reader.Reader;
import writer.Writer;

import java.io.IOException;

public class Formatter {
    private StringBuilder sb;
    private int currentIndex;
    private int tabAmount;
    private final static String OPEN = "{";
    private final static String CLOSED = "}";
    private final static String DOT = ";";
    private final static String TAB = "    ";
    private final static char NEW_LINE = '\n';
    private final static char SPACE = ' ';


    public Formatter() {
        sb = new StringBuilder();
        currentIndex = 0;
    }

    public String makeItClear(Reader reader, Writer writer) throws IOException {
        sb = read(reader);
        simpleCheck();
        refactor();
        makeClosedRight();
        write(writer);
        return sb.toString();
    }

    public void simpleCheck() {
        if(StringUtils.countMatches(sb.toString(), CLOSED) != StringUtils.countMatches(sb.toString(), OPEN)) {
            throw new InvalidCodeException("The number of '{' does not match the number of '}'");
        }
    }


    private void refactor() {
        if (sb.indexOf(OPEN, currentIndex) != -1) {
            currentIndex = sb.indexOf(OPEN, currentIndex);
        }
        tabAmount++;
        do {
            checkTabAmount();
        } while (checkNext());
    }

    // Метод проверяет, какой идёт следующий опорный элемент
    // Если ';', то продолжает форматирование текста без увеличения отступов
    // Если '{', то заново вызывает метод refactor(), увеличивая необходимое количество отступов
    // Если опорные элементы не найдены, выходит из метода
    private Boolean checkNext() {
        int closed = sb.indexOf(DOT, currentIndex);
        int recursion = sb.indexOf(OPEN, currentIndex);
        if (closed < recursion || recursion == -1 && closed != -1) {
            currentIndex = closed;
            return true;
        } else if (recursion != -1) {
            refactor();
            tabAmount--;
            return false;
        }
        return false;
    }

    // Метод для выравнивания '}'
    private void makeClosedRight() {
        currentIndex = 0;
        tabAmount = StringUtils.countMatches(sb.toString(), CLOSED) - 1;
        for (; tabAmount >= 0; tabAmount--) {
            correctClosed(sb.indexOf(CLOSED, currentIndex));
        }
    }

    // Метод проверяет количество отступов перед '}'
    private void correctClosed(int closed) {
        int i = 1;
        while (sb.charAt(closed - i) == SPACE || sb.charAt(closed - i) == NEW_LINE) {
            i++;
        }
        if (i != (tabAmount - 1) * 4) {
            insertLeftTab(closed - i + 1, closed);
        }
        currentIndex = sb.indexOf(CLOSED, currentIndex) + 1;
    }


    // Метод необходимый для удаления мусора справа от опорного индекса
    // и выравнивает табуляцию
    private void insertLeftTab(int start, int stop) {
        sb.delete(start, stop);
        for (int q = 0; q < tabAmount; q++) {
            sb.insert(start, TAB);
        }
        sb.insert(start, NEW_LINE);
    }

    // Метод проверяет количество отступов перед строкой
    private void checkTabAmount() {
        int i = 1;
        while (sb.charAt(currentIndex + i) == SPACE || sb.charAt(currentIndex + i) == NEW_LINE) {
            i++;
        }
        if (i != tabAmount * 4) {
            insertRightTab(i);
            currentIndex += tabAmount * 4;
        }
    }

    // Метод необходимый для удаления мусора слева от опорного индекса
    // и выравнивает отступы
    private void insertRightTab(int i) {
        sb.delete(currentIndex + 1, currentIndex + i);
        for (int q = 0; q < tabAmount; q++) {
            sb.insert(currentIndex + 1, TAB);
        }
        sb.insert(currentIndex + 1, NEW_LINE);
    }

    public StringBuilder read(Reader reader) throws IOException {
        StringBuilder tempSb = new StringBuilder();
        while (reader.hasChar()) {
            tempSb.append(reader.readChar());
        }
        return tempSb;
    }

    public void write(Writer writer) throws IOException {
        char[] writeChar = sb.toString().toCharArray();
        for(char ch : writeChar) {
            writer.writeChar(ch);
        }
    }
}
