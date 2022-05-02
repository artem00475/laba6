import commands.CommandManager;

import java.io.IOException;

public class ServerCommandManager implements CommandManager {
    private boolean wasErrors=false;

    public void execute(String name,boolean hasArgument){
        if (!hasArgument){
            if (name.equals("help")){
                try {
                    Server.sendManager.sendAnswer(helpCommand(),wasErrors);
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
}
