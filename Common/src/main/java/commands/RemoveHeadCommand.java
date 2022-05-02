package commands;

import ru.itmo.lab5.collection.CollectionManager;

/**
 * Команда, удаляющая первый элемент в очереди
 */
public class RemoveHeadCommand implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param collectionManager менеджер коллекции
     * @see CollectionManager
     */
    public RemoveHeadCommand(CollectionManager collectionManager){
        this.collectionManager=collectionManager;
    }

    @Override
    public boolean hasArgement() {
        return false;
    }

    @Override
    public String getName() {
        return "remove_head";
    }

    @Override
    public String getDescription() {
        return "вывести первый элемент коллекции и удалить его";
    }

    @Override
    public void execute(Boolean argument) {
        System.out.println(collectionManager.removeFirstElement().toString());
    }
}
