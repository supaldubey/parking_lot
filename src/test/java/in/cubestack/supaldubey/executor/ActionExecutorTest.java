package in.cubestack.supaldubey.executor;

import in.cubestack.supaldubey.action.ParkAction;
import in.cubestack.supaldubey.domain.ActionType;
import in.cubestack.supaldubey.registry.ActionRegistry;
import in.cubestack.supaldubey.registry.DefaultInMemoryRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ActionExecutorTest {

    private ActionRegistry actionRegistry;
    private ResultListener resultListener;

    private String result;

    @Before
    public void init() {
        actionRegistry = new DefaultInMemoryRegistry();
        result = "";
        resultListener = new ResultListener() {
            @Override
            public void onResult(String response) {
                result = response;
            }

            @Override
            public void onError(Exception ex) {
                result = ex.getMessage();
            }
        };
    }

    @Test
    public void testSetup() {
        ActionExecutor actionExecutor = new DefaultActionExecutor(actionRegistry, resultListener);
        actionExecutor.executeAction(null);

        Assert.assertEquals(result, "Input not provided for execution");
    }

    @Test
    public void testParkingCreation() {
        new ParkAction(actionRegistry);
        ActionExecutor actionExecutor = new DefaultActionExecutor(actionRegistry, resultListener);
        actionExecutor.executeAction(ActionType.CREATE_LOT.getActionName() + " 7");

        Assert.assertEquals(result, "Created a parking lot with 7 slots");
    }


    @Test
    public void testParkingActionWithoutLot() {
        new ParkAction(actionRegistry);
        ActionExecutor actionExecutor = new DefaultActionExecutor(actionRegistry, resultListener);
        actionExecutor.executeAction(ActionType.PARK.getActionName() + " REG COLOR");

        Assert.assertEquals(result, "No Parking lot created");
    }

    @Test
    public void testSetupNoActions() {
        ActionExecutor actionExecutor = new DefaultActionExecutor(actionRegistry, resultListener);
        actionExecutor.executeAction("abs");

        Assert.assertEquals(result, "No action registry provided or is empty");
    }

    @Test
    public void testParkingAction() {
        new ParkAction(actionRegistry);
        ActionExecutor actionExecutor = new DefaultActionExecutor(actionRegistry, resultListener);
        actionExecutor.executeAction(ActionType.CREATE_LOT.getActionName() + " 10");
        Assert.assertEquals(result, "Created a parking lot with 10 slots");

        actionExecutor.executeAction(ActionType.PARK.getActionName() + " REG COLOR");
        Assert.assertEquals(result, "Allocated slot number: 1");
    }
}
