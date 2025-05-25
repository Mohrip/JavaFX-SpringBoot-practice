package com.JavaFxAPPS.ui;

import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.springframework.stereotype.Component;
import java.util.List;



@Component
public class TodoUI extends VBox{
    private final ObservableList<String> items = FXCollections.observableArrayList();
    private final ListView<String> listView = new ListView<>(items);

    public TodoUI() {
        setSpacing(10);
        setPadding(new Insets(10));

        TextField input = new TextField();
        input.setPromptText("Enter a new todo item");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String text = input.getText();
            if (!text.isEmpty()) {
                items.add(text);
                input.clear();
            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            int idx = listView.getSelectionModel().getSelectedIndex();
            if (idx >= 0 ) items.remove(idx);
        });

        HBox controls = new HBox(10, input, addButton, deleteButton);
        getChildren().addAll(new Label("Todo List"), listView, controls);
        }
    }

