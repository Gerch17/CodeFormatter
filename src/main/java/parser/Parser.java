package parser;

import exceptions.InvalidCodeException;
import org.apache.commons.lang3.StringUtils;

public class Parser {
    private StringBuilder sb;
    private int currentIndex;
    private int tabAmount;

    public Parser(String code) {
        sb = new StringBuilder(code);
        currentIndex = 0;
    }

    public String makeItClear() throws InvalidCodeException {
        if (StringUtils.countMatches(sb.toString(), "{") != StringUtils.countMatches(sb.toString(), "}")) {
            throw new InvalidCodeException("The number of '{' does not match the number of '}'");
        }
        refactor();
        makeClosedRight();
        return sb.toString();
    }


    private void refactor() {
        if (sb.indexOf("{", currentIndex) != -1) {
            currentIndex = sb.indexOf("{", currentIndex);
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
        int closed = sb.indexOf(";", currentIndex);
        int recursion = sb.indexOf("{", currentIndex);
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
        tabAmount = StringUtils.countMatches(sb.toString(), "}") - 1;
        for (; tabAmount >= 0; tabAmount--) {
            correctClosed(sb.indexOf("}", currentIndex));
        }
    }

    // Метод проверяет количество отступов перед '}'
    private void correctClosed(int closed) {
        int i = 1;
        while (sb.charAt(closed - i) == ' ' || sb.charAt(closed - i) == '\n') {
            i++;
        }
        if (i != (tabAmount - 1) * 4) {
            insertLeftTab(closed - i + 1, closed);
        }
        currentIndex = sb.indexOf("}", currentIndex) + 1;
    }


    // Метод необходимый для удаления мусора справа от опорного индекса
    // и выравнивает табуляцию
    private void insertLeftTab(int start, int stop) {
        sb.delete(start, stop);
        for (int q = 0; q < tabAmount; q++) {
            sb.insert(start, "    ");
        }
        sb.insert(start, "\n");
    }

    // Метод проверяет количество отступов перед строкой
    private void checkTabAmount() {
        int i = 1;
        while (sb.charAt(currentIndex + i) == ' ' || sb.charAt(currentIndex + i) == '\n') {
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
            sb.insert(currentIndex + 1, "    ");
        }
        sb.insert(currentIndex + 1, "\n");
    }
}
