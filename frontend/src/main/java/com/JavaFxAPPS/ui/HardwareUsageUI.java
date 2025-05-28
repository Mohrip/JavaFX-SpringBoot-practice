package com.JavaFxAPPS.ui;

import com.JavaFxAPPS.JavaFxAPPS.HardwareUsageService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import io.github.palexdev.materialfx.controls.MFXButton;


import java.util.Map;

@Component
public class HardwareUsageUI extends VBox {

    private final HardwareUsageService hardwareUsageService;
   private final TextArea outputArea = new TextArea();

    private Runnable showHomeCallback;


    public HardwareUsageUI(HardwareUsageService hardwareUsageService) {
        this.hardwareUsageService = hardwareUsageService;
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

       // Button backButton = new Button("Back");
        MFXButton backButton = new MFXButton("Back");
        backButton.getStyleClass().addAll("mfx-button", "mfx-ripple");
        backButton.setStyle("-fx-background-color: #bdbdbd; -fx-background-radius: 20;");
        backButton.setOnAction(e -> {
            if (showHomeCallback != null) {
                showHomeCallback.run();
            }
        });

        MFXButton showButton = new MFXButton("Show Hardware Usage");
        showButton.getStyleClass().addAll("mfx-button", "mfx-ripple");
       // showButton.setStyle("-fx-background-color: #bdbdbd; -fx-background-radius: 20;");
        showButton.setStyle(
                "-fx-background-color: linear-gradient(to bottom, #4a90e2, #357ABD);" +  // blue gradient
                        "-fx-background-radius: 20;" +
                        "-fx-padding: 10 20;" +  // more padding
                        "-fx-text-fill: white;" +  // white text for contrast
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 4, 0, 0, 2);"
        );

        showButton.setOnAction(e -> showHardwareUsage());

        outputArea.setEditable(false);
        outputArea.setPrefColumnCount(10);

       getChildren().addAll(showButton, outputArea, backButton);
    }

    private void showHardwareUsage() {
        Map<String, Object> usage = hardwareUsageService.getHardwareUsage();
        StringBuilder sb = new StringBuilder();
        usage.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
        outputArea.setText(sb.toString());
    }

    public void setShowHomeCallback(Runnable showHomeCallback) {
        this.showHomeCallback = showHomeCallback;
    }
}