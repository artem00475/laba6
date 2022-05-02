package commands;

import ru.itmo.lab5.collection.CollectionManager;
import ru.itmo.lab5.person.Person;

/**
 * Команда, выводящая все элементы коллекции
 */
public class ShowCommand implements Command{
    private final CollectionManager collectionManager;

    /**
     * Конструктор, задающий параметры объекта
     * @param collectionManager менеджер коллекции
     * @see CollectionManager
     */
    public ShowCommand(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public boolean hasArgement() {
        return false;
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public void execute(Boolean argument) {
        if (collectionManager.getCollection().isEmpty()) {
            System.out.printf("Нельзя выполнить команду %s: коллекция пустая\n", getName());
        } else {
            System.out.println("Все элементы коллекции: ");
            for (Person person : collectionManager.getCollection()) {
                System.out.println(person);
            }
        }
    }




}
