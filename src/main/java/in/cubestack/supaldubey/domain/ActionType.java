package in.cubestack.supaldubey.domain;

public enum  ActionType {
    PARK("park"),
    CREATE_LOT("create_parking_lot"),
    LEAVE("leave"),
    STATUS("status"),
    REGISTRATION_LOOKUP_COLOR("registration_numbers_for_cars_with_colour"),
    SLOT_LOOKUP_COLOR("slot_numbers_for_cars_with_colour"),
    SLOT_LOOKUP_REGISTRATION("slot_number_for_registration_number");

    private final String actionName;

    private ActionType(String actionName) {
        this.actionName = actionName;
    }

    public String getActionName() {
        return actionName;
    }
}
