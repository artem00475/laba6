import collection.CollectionManager;
import commands.Command;
import commands.CommandManager;
import person.Person;

import java.io.IOException;

public class ServerCommandManager implements CommandManager {
    private boolean wasErrors=false;
    private SendManager sendManager;
    private CollectionManager collectionManager;

    public ServerCommandManager(SendManager sendManager,CollectionManager collectionManager){
        this.sendManager=sendManager;
        this.collectionManager=collectionManager;
    }

    public void execute(Command command, boolean hasArgument) {
        if (!hasArgument) {
            if (command.getName().equals("help")) {
                try {
                    sendManager.sendAnswer(helpCommand(), wasErrors);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (command.getName().equals("info")) {
                try {
                    sendManager.sendAnswer(infoCommand(), wasErrors);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.getName().equals("show")) {
                try {
                    sendManager.sendAnswer(showCommand(), wasErrors);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (command.getName().equals("clear")) {
                try {
                    sendManager.sendAnswer(clearCommand(), wasErrors);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (command.getName().equals("remove_head")){
                try {
                    sendManager.sendAnswer(removeHeadCommand(), wasErrors);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (command.getName().equals("print_field_ascending_location")){
                try {
                    sendManager.sendAnswer(printFieldAscendingLocationCommand(), wasErrors);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String helpCommand(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("help : вывести справку по доступным командам\n");
        stringBuilder.append("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)\n");
        stringBuilder.append("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении\n");
        stringBuilder.append("add {element} : добавить новый элемент в коллекцию\n");
        stringBuilder.append("update id {element} : обновить значение элемента коллекции, id которого равен заданному\n");
        stringBuilder.append("remove_by_id id : удалить элемент из коллекции по его id\n");
        stringBuilder.append("clear : очистить коллекцию\n");
        stringBuilder.append("execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.\n");
        stringBuilder.append("exit : завершить работу \n");
        stringBuilder.append("remove_head : вывести первый элемент коллекции и удалить его\n");
        stringBuilder.append("add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n");
        stringBuilder.append("remove_greater {element} : удалить из коллекции все элементы, превышающие заданный\n");
        stringBuilder.append("count_greater_than_location location : вывести количество элементов, значение поля location которых больше заданного\n");
        stringBuilder.append("filter_less_than_eye_color eyeColor : вывести элементы, значение поля eyeColor которых меньше заданного\n");
        stringBuilder.append("print_field_ascending_location : вывести значения поля location всех элементов в порядке возрастания\n");
        return stringBuilder.toString();
    }

    public String infoCommand(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Информация о коллекции:\n");
        stringBuilder.append("Коллекция типа PriorityQueue, в которой хранятся объекты класса Person\n");
        stringBuilder.append("Дата инициализации: "+collectionManager.getInitDate()+"\n");
        stringBuilder.append("Количестов элементов: "+collectionManager.getCollection().size()+"\n");
        return stringBuilder.toString();
    }

    public String showCommand(){
        StringBuilder stringBuilder = new StringBuilder();
        if (collectionManager.getCollection().isEmpty()) {
            stringBuilder.append("Нельзя выполнить команду show: коллекция пустая\n");
        } else {
            stringBuilder.append("Все элементы коллекции: \n");
            for (Person person : collectionManager.getCollection()) {
                stringBuilder.append(person+"\n");
            }
        }
        return stringBuilder.toString();
    }

    public String clearCommand(){
        collectionManager.removeAll();
        return "Коллекция очищена";
    }

    public String removeHeadCommand() {
        if (collectionManager.getCollection().isEmpty()) {
            return "Коллекция пуста";
        } else {
            return collectionManager.removeFirstElement().toString();
        }
    }

    public String printFieldAscendingLocationCommand(){
        StringBuilder stringBuilder = new StringBuilder();
        if (collectionManager.getCollection().isEmpty()) {
            return "Коллекция пуста";
        } else {
            for (Person person : collectionManager.sortByLocation()){
                stringBuilder.append(person.getLocation()+"\n");
            }
        }return stringBuilder.toString();
    }
}
