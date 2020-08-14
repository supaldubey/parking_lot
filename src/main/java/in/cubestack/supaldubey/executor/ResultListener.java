package in.cubestack.supaldubey.executor;

public interface ResultListener {
    void onResult(String response);
    void onError(Exception ex);
}
