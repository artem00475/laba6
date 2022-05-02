package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.console.ConsoleManager;
import ru.itmo.lab5.file.ScriptManager;

/**
 * Команда, удаляющая элемент по id
 */
public class RemoveByIdCommand implements Command {
    private final ConsoleManager consoleManager;
    private final CollectionManager collectionManager;
    private final ScriptManager scriptManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param collectionManager менеджер коллекции
     * @see CollectionManager
     * @param consoleManager менеджер консоли
     * @see ConsoleManager
     * @param scriptManager менеджер скрипта
     * @see ScriptManager
     */
    public RemoveByIdCommand(CollectionManager collectionManager, ConsoleManager consoleManager, ScriptManager scriptManager){
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
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }

    @Override
    public void execute(Boolean argument) {
        if (argument) {
                int id = consoleManager.getID();
                collectionManager.removeElementByID(id);
                System.out.println("Элемент успешно удалён.");
        }else {
            try {
                int id = scriptManager.getID();
                collectionManager.removeElementByID(id);
                System.out.println("Элемент успешно удалён.");
            }catch (Exception e) {System.out.printf("Команда %s не выполнена\n", getName());}
        }
    }
}
