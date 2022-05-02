package commands;

import ru.itmo.lab5.Main;
import ru.itmo.lab5.exceptions.ScriptException;
import ru.itmo.lab5.file.ScriptManager;

import java.io.File;
import java.util.Deque;

/**
 * Команда, выполняющая скрипт
 */
public class ExcecuteCommand implements Command {
    private final ScriptManager scriptManager;
    private final Deque<String> deque;

    /**
     * Конструктор, задающий параметры объекта
     * @param scriptManager менеджер скрипта
     * @see ScriptManager
     * @param deque очередь команд из скрипта
     */
    public ExcecuteCommand(ScriptManager scriptManager, Deque<String> deque){
        this.scriptManager = scriptManager;
        this.deque=deque;
    }

    @Override
    public boolean hasArgement() {
        return true;
    }

    @Override
    public String getName() {
        return "execute_script";
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме";
    }

    @Override
    public void execute(Boolean argument) {
        if (argument){
            System.out.print("Введите полное имя файла: ");
            String path = Main.scanner.nextLine();
            try {
                if (path.equals("")) {throw new ScriptException("Ничего не введено");}
                File file = new File(path);
                scriptManager.addFile(file);
            }catch (ScriptException e) {
                System.out.printf("Команда %s не выполнена\n", getName());
            }
        }else {
            try {
                String path = deque.remove();
                File file = new File(path);
                scriptManager.addFile(file);
            }catch (Exception e) {
                System.out.printf("Команда %s не выполнена\n", getName());
            }
        }
    }
}
