package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.console.ConsoleManager;
import ru.itmo.lab5.file.ScriptManager;

/**
 * Команда, обновляющая элемент по id
 */
public class UpdateCommand implements Command{
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
    public UpdateCommand(ConsoleManager consoleManager, CollectionManager collectionManager, ScriptManager scriptManager){
        this.consoleManager = consoleManager;
        this.collectionManager = collectionManager;
        this.scriptManager=scriptManager;
    }

    @Override
    public boolean hasArgement() {
        return true;
    }

    @Override
    public String getName() {
        return "update id";
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public void execute(Boolean argument) {
        if (argument) {
                int id = consoleManager.getID();
                collectionManager.updateElement(id, consoleManager.getPersonFromConsole());
                System.out.println("Элемент успешно обновлен");
        }else {
            try {
                int id = scriptManager.getID();
                collectionManager.updateElement(id, scriptManager.getPersonFromScript());
                System.out.println("Элемент успешно оновлен");
            }catch (Exception e) {System.out.printf("Команда %s не выполнена\n", getName());}
        }
    }
}
