package broker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;

public class Broker {

    private boolean isString;
    private String path;

    public String returnResult(String code) {
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            if (!isString) {
                writer.write(code);
                writer.close();
                return "Success";
            }
            return code;
        } catch (IOException e) {
            return code;
        }
    }

    public String receiveFile(String path) {
        try {
            this.path = path;
            return Files.readString(Path.of(path));
        } catch (InvalidPathException | IOException e) {
            isString = true;
            return path;
        }
    }
}
