package Handlers;

import Action.Action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ActionHandler {
    private final static List<Action> actions = new ArrayList<>();

    public static void add(Action action) {
        actions.add(action);
    }


    public ArrayList<Action> getActionsFromClass(Action action) {
        return actions.stream().filter(action1 -> action1.getClass().equals(action.getClass())).collect(Collectors.toCollection(ArrayList::new));
    }

    public static void update() {
        actions.clear();
    }


}
