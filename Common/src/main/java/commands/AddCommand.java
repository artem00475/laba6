package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.console.ConsoleManager;
import ru.itmo.lab5.file.ScriptManager;

/**
 * Команда, добавляющая элемент в коллекцию
 */
public class AddCommand implements Command {
    private final ConsoleManager consoleManager;
    private final CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param consoleManager  менеджер консоли
     * @see ConsoleManager
     * @param collectionManager менеджер коллекуии
     * @see CollectionManager
     * @param scriptManager менеджер скрипта
     * @see ScriptManager
     */
    public AddCommand( ConsoleManager consoleManager, CollectionManager collectionManager, ScriptManager scriptManager)  {
        this.consoleManager = consoleManager;
        this.collectionManager=collectionManager;
        this.scriptManager=scriptManager;
    }

    @Override
    public boolean hasArgement() {
        return true;
    }

    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }

    @Override
    public void execute(Boolean argument) {
        if (argument) {
                collectionManager.addElement(consoleManager.getPersonFromConsole());
                System.out.println("Элемент успешно добавлен");
        }else{
            try {
                collectionManager.addElement(scriptManager.getPersonFromScript());
                System.out.println("Элемент успешно добавлен");
            }catch (Exception e) {System.out.printf("Команда %s не выполнена\n", getName());}
        }
    }
}
