package com.JavaFxAPPS.ui;

import com.JavaFxAPPS.JavaFxAPPS.HardwareUsageService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HardwareUsageUI extends VBox {

    private final HardwareUsageService hardwareUsageService;
    private final TextArea outputArea = new TextArea();

    public HardwareUsageUI(HardwareUsageService hardwareUsageService) {
        this.hardwareUsageService = hardwareUsageService;
        setSpacing(10);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);

        Button showButton = new Button("Show Hardware Usage");
        showButton.setOnAction(e -> showHardwareUsage());

        outputArea.setEditable(false);
        outputArea.setPrefRowCount(10);

        getChildren().addAll(showButton, outputArea);
    }

    private void showHardwareUsage() {
        Map<String, Object> usage = hardwareUsageService.getHardwareUsage();
        StringBuilder sb = new StringBuilder();
        usage.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
        outputArea.setText(sb.toString());
    }
}