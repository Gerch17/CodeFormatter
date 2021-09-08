package format.analysis.tools.external;

import format.analysis.tools.ICommand;
import format.analysis.tools.State;
import format.analysis.tools.external.utils.YamlConstructor;
import format.analysis.tools.externalmodels.LexerCommand;
import format.analysis.tools.externalmodels.LexerCommands;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.javatuples.Pair;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

@Slf4j
public class ExternalCommandRepository {
    private static final String COMMAND_PACKAGE = "format.analysis.tools.commands";
    private Map<Pair<State, String>, ICommand> commands;
    private Yaml yaml;

    public ExternalCommandRepository(String commandPath) {
        yaml = new Yaml(new YamlConstructor<>(LexerCommands.class));
        commands = new HashMap<>();
        initCommands(commandPath);
    }

    public ICommand getCommand(State state, Character ch) {
        ICommand command = commands.get(new Pair<>(state, String.valueOf(ch)));
        if (command == null) {
            command = commands.get(new Pair<>(state, null));
        }
        return command;
    }

    private void initCommands(String commandPath) {
        try {
            InputStream inputStream = new FileInputStream(commandPath);
            List<LexerCommands> lexerCommandsList = yaml.load(inputStream);
            for (LexerCommands command : lexerCommandsList) {
                String state = command.getState();
                for (LexerCommand lexerCommand : command.getCommands()) {
                    ICommand commandToMap = createCommand(String.valueOf(lexerCommand.getCommand()));
                    commands.put(new Pair(new State(state), lexerCommand.getCh()), commandToMap);
                }
            }
        } catch (FileNotFoundException e) {
            log.error("Cannot find file by path: {}", COMMAND_PACKAGE);
        } catch (ClassNotFoundException e) {
            log.error("Cannot find class by path: {}", COMMAND_PACKAGE);
        }
    }

    private ICommand createCommand(final String className) throws ClassNotFoundException {
        String fullName = COMMAND_PACKAGE + "." + className;
        try {
            return (ICommand) Class.forName(fullName).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            log.error("Instance exception: {}", fullName);
        } catch (NoSuchMethodException e) {
            log.error("Here is no such method: {}", fullName);
        } catch (InvocationTargetException e) {
            log.error("Cannot create command with name: {}", fullName);
        }
        return null;
    }


}
