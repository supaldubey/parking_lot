package in.cubestack.supaldubey.executor;

import in.cubestack.supaldubey.action.ParkingAction;
import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.domain.ParkingLot;
import in.cubestack.supaldubey.registry.ActionRegistry;

import static in.cubestack.supaldubey.Assertions.require;
import static in.cubestack.supaldubey.Assertions.requireNotNull;

public class DefaultActionExecutor implements ActionExecutor {

    private ParkingLot parkingLot;
    private final ActionRegistry actionRegistry;
    private final ResultListener resultListener;


    public DefaultActionExecutor(ActionRegistry actionRegistry, ResultListener resultListener) {
        this.actionRegistry = actionRegistry;
        this.resultListener = resultListener;
    }

    @Override
    public void executeAction(String input) {
        try {
            requireNotNull(input, "Input not provided for execution");
            require(actionRegistry != null && actionRegistry.hasActions(), "No action registry provided or is empty");

            //Split action by spaces, first argument is command and rest arguments
            String[] actionCommand = input.split(" ");
            String coreAction = actionCommand[0];

            // Special case for creating parking lot
            if (coreAction.equals(ActionType.CREATE_LOT.getActionName())) {
                validateAndCreateLot(actionCommand);
            } else {
                ParkingAction parkingAction = actionRegistry.getActionFor(coreAction);
                if (parkingAction == null) {
                    throw new RuntimeException("Invalid command. Provided: " + coreAction);
                }
                String response = parkingAction.perform(parkingLot, actionCommand);
                resultListener.onResult(response);
            }

        } catch (Exception ex) {
            resultListener.onError(ex);
        }
    }

    private void validateAndCreateLot(String[] actionCommand) {
        require(actionCommand.length == 2, "Invalid command for creating parking lot, missing number");
        int parkingSize = Integer.parseInt(actionCommand[1]);
        parkingLot = new ParkingLot(parkingSize);

        resultListener.onResult(String.format("Created a parking lot with %s slots", parkingSize));
    }
}
