package ir.fallahpoor.demolisher;

import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Masood Fallahpoor
 */
public class Runner {

    public static void main(String[] args) {

        CommandLine commandLine = null;
        Options options = DemolisherOptions.getOptions();
        HelpFormatter formatter = new HelpFormatter();

        try {
            commandLine = new DefaultParser().parse(options, args);

            if (commandLine.hasOption(DemolisherOptions.OPTION_VERSION)) {
                System.out.println(DemolisherOptions.VERSION_MESSAGE);
                System.exit(0);
            }

        } catch (ParseException e) {
            formatter.printHelp(DemolisherOptions.USAGE_MESSAGE, options);
            System.exit(1);
        }

        List<String> argumentsList = commandLine.getArgList();

        if (argumentsList.size() < DemolisherOptions.NUM_MANDATORY_ARGUMENTS) {
            formatter.printHelp(DemolisherOptions.USAGE_MESSAGE, options);
            System.exit(1);
        } else {
            String directoryPath = argumentsList.get(0);
            ArrayList<String> fileNames = new ArrayList<>();
            fileNames.addAll(commandLine.getArgList().subList(1, argumentsList.size()));

            Demolisher demolisher = new Demolisher(directoryPath, fileNames, commandLine);
            demolisher.demolish();
        }

    } // end of method main

} //end of class Runner
