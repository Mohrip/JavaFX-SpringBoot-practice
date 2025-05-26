package com.JavaFxAPPS.ui;

import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import org.springframework.stereotype.Component;
import javafx.scene.control.*;
import javafx.scene.layout.*;



@Component
public class TodoUI extends VBox{
    private final TreeItem<String> rootItem = new TreeItem<>("");
    private final TreeView<String> treeView = new TreeView<>(rootItem);

    private Runnable showHomeCallback;


    public TodoUI() {
        setSpacing(10);
        setPadding(new Insets(10));

        treeView.setShowRoot(false);


        TextField input = new TextField();
        input.setPromptText("Enter a new todo item");

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            String text = input.getText();
            if (!text.isEmpty()) {
                TreeItem<String> selected = treeView.getSelectionModel().getSelectedItem();
                TreeItem<String> newItem = new TreeItem<>(text);
                if (selected != null && selected != rootItem) {
                    selected.getChildren().add(newItem);
                    selected.setExpanded(true);
                }
                else {
                    rootItem.getChildren().add(newItem);
                }
                input.clear();
                treeView.getSelectionModel().clearSelection();

            }
        });

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e -> {
            TreeItem<String> selected = treeView.getSelectionModel().getSelectedItem();
            if (selected != null && selected != rootItem) {
                selected.getParent().getChildren().remove(selected);
            }
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            if (showHomeCallback != null) {
                showHomeCallback.run();
            }
        });



        HBox controls = new HBox(10, input, addButton, deleteButton, backButton);
        getChildren().addAll(new Label("Todo List"), treeView, controls);
        rootItem.setExpanded(true);
        }

    public void setShowHomeCallback(Runnable showHomeCallback) {
        this.showHomeCallback = showHomeCallback;
    }
    }

