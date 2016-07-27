package shone.sing.lone.todo;

import shone.sing.lone.todo.utils.CommandLineInput;
import shone.sing.lone.todo.utils.CommandLineInputHandler;

/**
 * Created by XingLong on 2016/7/27.
 */


public class ToDoApp {
    public static final char DEFAULT_INPUT = '\u0000';

    public static void main(String args[]) {
        CommandLineInputHandler commandLineInputHandler = new CommandLineInputHandler();
        char command = DEFAULT_INPUT;

        while (CommandLineInput.EXIT.getShortCmd() != command) {
            commandLineInputHandler.printOptions();
            String input = commandLineInputHandler.readInput();
            char[] inputChars = input.length() == 1 ? input.toCharArray() : new char[]{DEFAULT_INPUT};
            command = inputChars[0];
            CommandLineInput commandLineInput = CommandLineInput.getCommandLineInputForInput(command);
            commandLineInputHandler.processInput(commandLineInput);
        }
    }
}