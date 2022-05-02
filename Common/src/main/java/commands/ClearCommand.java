package commands;

import ru.itmo.lab5.collection.CollectionManager;

/**
 * Команда, очищающая коллекцию
 */
public class ClearCommand implements Command {
    private final CollectionManager collectionManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param collectionManager менеджер коллекции
     * @see CollectionManager
     */
    public ClearCommand(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    @Override
    public boolean hasArgement() {
        return false;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }

    @Override
    public void execute(Boolean argument) {
        collectionManager.removeAll();
        System.out.println("Коллекция очищена");
    }
}
