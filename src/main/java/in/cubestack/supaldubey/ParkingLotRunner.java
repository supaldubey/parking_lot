package in.cubestack.supaldubey;

import in.cubestack.supaldubey.action.*;
import in.cubestack.supaldubey.executor.ActionExecutor;
import in.cubestack.supaldubey.executor.ConsoleResultListener;
import in.cubestack.supaldubey.executor.DefaultActionExecutor;
import in.cubestack.supaldubey.executor.ResultListener;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class ParkingLotRunner {

    private ActionRegistry actionRegistry;
    private ActionExecutor actionExecutor;

    public static void main(String[] input) throws Exception {
        ParkingLotRunner parkingLotRunner = new ParkingLotRunner();

        parkingLotRunner.initializeAndRun(input);
    }

    private void initializeAndRun(String[] input) throws Exception {
        initializeActions();
        if (input == null || input.length == 0) {
            runInteractive();
        } else {
            readFileAndExecuteBatch(input);
        }
    }

    private void initializeActions() {

        actionRegistry = new DefaultInMemoryRegistry();
        ResultListener resultListener = new ConsoleResultListener();
        actionExecutor = new DefaultActionExecutor(actionRegistry, resultListener);

        createActions();
    }

    private void createActions() {
        // Register all actions by creating them
        new ParkAction(actionRegistry);
        new ColorLookupForRegistrationAction(actionRegistry);
        new LeaveParkingAction(actionRegistry);
        new ParkingStatusAction(actionRegistry);
        new RegistrationNumberLookupAction(actionRegistry);
        new SlotNumberByColorAction(actionRegistry);
    }

    private void readFileAndExecuteBatch(String[] input) throws Exception {
        String fileLocation = input[0];
        Files.lines(Paths.get(fileLocation)).forEach(this::performAction);
    }

    private void runInteractive() {
        Scanner scanner = new Scanner(System.in);
        String inputCommand = scanner.nextLine();
        while (true) {
            if ("exit".equalsIgnoreCase(inputCommand)) {
                System.exit(0);
            }

            performAction(inputCommand);
            inputCommand = scanner.nextLine();
        }
    }

    private void performAction(String inputCommand) {
        actionExecutor.executeAction(inputCommand);
    }
}
