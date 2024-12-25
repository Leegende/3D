package Action;

public class InputAction extends Action {

    public final int INPUT_ACTION;
    private final InputDeviceEnum inputDeviceEnum;
    private final InputActionEnum inputActionEnum;

    public InputAction(int INPUT_ACTION, InputDeviceEnum inputDeviceEnum, InputActionEnum inputActionEnum) {
        this.INPUT_ACTION = INPUT_ACTION;
        this.inputDeviceEnum = inputDeviceEnum;
        this.inputActionEnum = inputActionEnum;
    }
}
