package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.console.ConsoleManager;
import ru.itmo.lab5.file.ScriptManager;

/**
 * Команда, выводящая количество элементов, значение которых больше заданного
 */
public class CountGreaterThanLocationCommand implements Command {
    private final ConsoleManager consoleManager;
    private final CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param consoleManager менеджер консоли
     * @see ConsoleManager
     * @param collectionManager менеджер коллекции
     * @see CollectionManager
     * @param scriptManager менеджер скрипта
     * @see ScriptManager
     */
    public CountGreaterThanLocationCommand(ConsoleManager consoleManager, CollectionManager collectionManager, ScriptManager scriptManager) {
        this.collectionManager = collectionManager;
        this.consoleManager = consoleManager;
        this.scriptManager=scriptManager;
    }

    @Override
    public boolean hasArgement() {
        return true;
    }

    @Override
    public String getName() {
        return "count_greater_than_location";
    }

    @Override
    public String getDescription() {
        return "вывести количество элементов, значение поля location которых больше заданного";
    }

    @Override
    public void execute(Boolean argument) {
        if (argument) {
                System.out.println(collectionManager.countGreaterLocation(consoleManager.getLocationFromConsole()));
        }else {
            try {
                System.out.println(collectionManager.countGreaterLocation(scriptManager.getLocationFromScript()));
            }catch (Exception e) {System.out.printf("Команда %s не выполнена\n", getName());}
        }
    }
}
