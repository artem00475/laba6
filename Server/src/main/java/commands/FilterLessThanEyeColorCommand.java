package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.console.ConsoleManager;
import ru.itmo.lab5.file.ScriptManager;
import ru.itmo.lab5.person.Person;

/**
 * Команда, выводящая элементы, значение которых меньше заданного
 */
public class FilterLessThanEyeColorCommand implements Command {
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
    public FilterLessThanEyeColorCommand(ConsoleManager consoleManager, CollectionManager collectionManager, ScriptManager scriptManager) {
        this.consoleManager = consoleManager;
        this.collectionManager = collectionManager;
        this.scriptManager = scriptManager;
    }

    @Override
    public boolean hasArgement() {
        return true;
    }

    @Override
    public String getName() {
        return "filter_less_than_eye_color";
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля eyeColor которых меньше заданного";
    }

    @Override
    public void execute(Boolean argument) {
        if (argument) {
                for (Person person : collectionManager.filterLessThanEyeColor(consoleManager.getEyeColor())) {
                    System.out.println(person.toString());
                }
        } else {
            try {
                for (Person person : collectionManager.filterLessThanEyeColor(scriptManager.getEyeColor())) {
                    System.out.println(person.toString());
                }
            }catch (Exception e) {System.out.printf("Команда %s не выполнена\n", getName());}
        }
    }
}
