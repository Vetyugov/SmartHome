package Commands;

import publicServices.ConnectionService;

import java.util.Stack;

public class CommandsHandler {
    private static CommandsHandler instance;

    private final Stack<Command> stack = new Stack<>();

    public static CommandsHandler getInstance(){
        CommandsHandler localInstance = instance;
        if(localInstance == null){
            synchronized (ConnectionService.class){
                localInstance = instance;
                if (localInstance == null){
                    localInstance = instance = new CommandsHandler();
                }
            }
        }
        return localInstance;
    }

    private CommandsHandler(){}

    public void undo(){
        stack.pop().undo();
    }

    protected void setCommand(Command command){
        stack.push(command);
    }

}
