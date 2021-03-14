package Main_Logic;

import Commands.*;
import Exceptions.CommandNotFoundException;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Main logic class.
 * Connects other classes together.
 */
public class Application {

    /**
     * The store, which all actions will be performed on.
     */
    private final Store store;
    /**
     * An input scanner, created from an input file
     */
    Scanner inputScanner;
    /**
     * A file output stream, for directly redirecting program output to a file.
     */
    FileOutputStream output;
    /**
     * An output stream, for writing output.
     */
    PrintStream printOut;
    /**
     * A hash map of commands, which stores all possible commands on the store.
     */
    private HashMap<String, Command> commands;

    /**
     * One parameter constructor.
     * Gets the store single instance and initialises commands in the command
     * map and the input scanner, based on the parameter.
     *
     * @param source An input stream, that the input scanner will be
     *               initialised with.
     */
    Application(InputStream source) {
        store = Store.getInstance();
        initCommands();
        inputScanner = new Scanner(source);
    }


    /**
     * One parameter constructor.
     * Gets the store single instance and initialises commands in the command
     * map and the input scanner, based on the parameter.
     *
     * @param inputFile An input file, that the input scanner will be
     *                  initialised with.
     */
    Application(File inputFile) {
        store = Store.getInstance();
        initCommands();
        try {
            inputScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method that runs first.
     * Creates an Application object and executes commands.
     *
     * @param args Array of strings representing the command line argument
     *             strings.
     */
    public static void main(String[] args) {
        Application app;

        //Checks number of main parameters in order to choose if the desired
        // input/output streams should be default or on files
        if (args.length > 1) {
            app = new Application(new File(args[0]));
            app.setSystemOutput(args[1], args[2]);
        } else {
            app = new Application(System.in);
        }

        app.executeCommands();
        app.closeOutputStreams();
    }

    /**
     * Private helper method.
     * Continuously reads lines from input, parses them in commands and
     * parameters and executes the commands through the objects in the hash map.
     */
    private void executeCommands() {
        boolean runningFlag = true;
        while (runningFlag) {

            //Parses the input command string, in parameters and actual command
            String[] commandString = inputScanner.nextLine().split(" ", 2);
            String command = commandString[0];
            String[] parameters = null;

            //If the command has parameters, they get parsed
            if (commandString.length > 1) {
                parameters = parseParameters(command, commandString[1]);
            }

            //Special case, for the exit/quit command
            if (command.equals("exit") || command.equals("quit")) {
                runningFlag = false;
            }

            //For all the other commands
            else if (commands.containsKey(command)) {
                commands.get(command).execute(store, parameters);
            }

            //No valid command found case
            else {
                try {
                    throw new CommandNotFoundException(
                            "Command " + command + " " + "is not valid.");
                } catch (CommandNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        inputScanner.close();
    }

    /**
     * Private helper method.
     * Checks the command and parses the parameters based on it.
     *
     * @param command          The actual command name, represented as a name.
     * @param parametersString The whole command line (contains both the
     *                         command and the parameters), represented as a
     *                         string.
     * @return The parameters, represented as an array of strings.
     */
    private String[] parseParameters(String command, String parametersString) {
        if (command.equals("adddiscount")) {
            return parametersString.split(" ", 3);
        } else {
            return parametersString.split(" ");
        }
    }

    /**
     * Private helper method.
     * Closes the output and print streams, if they have been opened.
     */
    private void closeOutputStreams() {
        if (printOut != null) {
            printOut.close();
        }

        if (output != null) {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Private helper method.
     * Initialises the output and print streams.
     *
     * @param out The filename of the file that the standard output streams
     *            will be opened with, represented as a string.
     * @param err The filename of the file that the error streams will be
     *            opened with, represented as a string.
     */
    private void setSystemOutput(String out, String err) {
        OutputStream output;
        PrintStream printOut = null;
        try {
            output = new FileOutputStream(out);
            printOut = new PrintStream(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setOut(printOut);

        OutputStream error;
        PrintStream printErr = null;
        try {
            error = new FileOutputStream(err);
            printErr = new PrintStream(error);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.setErr(printErr);
    }

    /**
     * Private helper method.
     * Initialises the commands map with all possible commands.
     * New commands can be easily added with the help of this method.
     */
    private void initCommands() {
        commands = new HashMap<>();
        commands.put("listcurrencies", new ListCurrencies());
        commands.put("getstorecurrency", new GetStoreCurrency());
        commands.put("addcurrency", new AddCurrency());
        commands.put("loadcsv", new LoadCSV());
        commands.put("savecsv", new SaveCSV());
        commands.put("setstorecurrency", new SetStoreCurrency());
        commands.put("updateparity", new UpdateParity());
        commands.put("listproducts", new ListProducts());
        commands.put("showproduct", new ShowProduct());
        commands.put("listmanufacturers", new ListManufacturers());
        commands.put("listproductsbymanufacturer", new ListProdByMan());
        commands.put("listdiscounts", new ListDiscounts());
        commands.put("adddiscount", new AddDiscount());
        commands.put("applydiscount", new ApplyDiscount());
        commands.put("calculatetotal", new CalculateTotal());
        commands.put("savestore", new SaveStore());
        commands.put("loadstore", new LoadStore());
    }
}
