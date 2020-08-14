package in.cubestack.supaldubey.executor;

public class ConsoleResultListener implements ResultListener {

    @Override
    public void onResult(String response) {
        System.out.println(response);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println(ex.getMessage());
    }
}
