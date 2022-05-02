package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.person.Person;

/**
 * Команда, выводящая значения поля location в порядке возрастания
 */
public class PrintFieldAscendingLocationCommand implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param collectionManager менеджер коллекции
     * @see CollectionManager
     */
    public PrintFieldAscendingLocationCommand(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean hasArgement() {
        return false;
    }

    @Override
    public String getName() {
        return "print_field_ascending_location";
    }

    @Override
    public String getDescription() {
        return "вывести значения поля location всех элементов в порядке возрастания";
    }

    @Override
    public void execute(Boolean argument) {
        for (Person person : collectionManager.sortByLocation()){
            System.out.println(person.getLocation());
        }

    }
}
