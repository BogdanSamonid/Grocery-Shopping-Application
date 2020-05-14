package controller;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;

public class LabelEventHandler implements EventHandler<Event>{
    @Override
    public void handle(Event actionEvent) {
        System.out.println(actionEvent.toString());
    }
}
