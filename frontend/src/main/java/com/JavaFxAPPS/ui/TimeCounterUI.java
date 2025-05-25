package com.JavaFxAPPS.ui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class TimeCounterUI extends VBox{
    private final Label label = new Label("Set your timer");
    private AnimationTimer timer;
    private long remainingMs = 0;
  //  private final Instant start = Instant.now();

    public TimeCounterUI() {
        setSpacing(10);
        setPadding(new Insets(10));
        label.setStyle("-fx-font-size: 20px;");
        //getChildren().addAll(new Label("Time Counter"), label);

        Label durationTitle = new Label("Set Duration:");
        durationTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");


        TextField yearsField = new TextField("0");
        TextField monthsField = new TextField("0");
        TextField weeksField = new TextField("0");
        TextField daysField = new TextField("0");
        TextField hoursField = new TextField("0");
        TextField minutesField = new TextField("0");
        TextField secondsField = new TextField("0");
        TextField millisecondsField = new TextField("0");

        HBox inputs = new HBox(10,
                new Label("Years:"), yearsField,
                new Label("Months:"), monthsField,
                new Label("Weeks:"), weeksField,
                new Label("Days:"), daysField,
                new Label("Hours:"), hoursField,
                new Label("Minutes:"), minutesField,
                new Label("Seconds:"), secondsField,
                new Label("Milliseconds:"), millisecondsField);

      //  inputs.setWrapText(true);
        Label controlsTitle = new Label("Controls");
        controlsTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");


        Button startBtn = new Button("Start Countdown");
        startBtn.setOnAction(e -> {
            // Parse all fields, default to 0 if invalid
            long years = parseLong(yearsField.getText());
            long months = parseLong(monthsField.getText());
            long weeks = parseLong(weeksField.getText());
            long days = parseLong(daysField.getText());
            long hours = parseLong(hoursField.getText());
            long minutes = parseLong(minutesField.getText());
            long seconds = parseLong(secondsField.getText());
            long millis = parseLong(millisecondsField.getText());

            remainingMs = millis
                    + seconds * 1000
                    + minutes * 60_000
                    + hours * 3_600_000
                    + days * 86_400_000
                    + weeks * 604_800_000
                    + months * 2_592_000_000L // 30 days per month
                    + years * 31_536_000_000L; // 365 days per year

            if (timer != null) timer.stop();
            if (remainingMs > 0) {
                long endTime = System.currentTimeMillis() + remainingMs;
                timer = new AnimationTimer() {
                    @Override
                    public void handle(long now) {
                        long msLeft = endTime - System.currentTimeMillis();
                        if (msLeft <= 0) {
                            label.setText("Countdown finished!");
                            stop();
                        } else {
                            label.setText(formatDuration(msLeft));
                        }
                    }
                };
                timer.start();
            } else {
                label.setText("Please enter a positive duration.");
            }
        });

        Button stopBtn = new Button("Stop Countdown");
        stopBtn.setOnAction(e -> {
            if (timer != null) {
                timer.stop();
                label.setText("Countdown stopped.");
            }

        }
        );

        HBox controls = new HBox(10, startBtn, stopBtn);
        getChildren().addAll(
                new Label("Countdown Timer"),
                durationTitle, inputs,
                controlsTitle, controls,
                label
        );



      //  getChildren().addAll(new Label("Countdown Timer"), inputs, startBtn, stopBtn, label);
    }

    private long parseLong(String s) {
        try { return Long.parseLong(s.trim()); } catch (Exception e) { return 0; }
    }

    private String formatDuration(long ms) {
        long years = ms / 31_536_000_000L;
        ms %= 31_536_000_000L;
        long months = ms / 2_592_000_000L;
        ms %= 2_592_000_000L;
        long weeks = ms / 604_800_000L;
        ms %= 604_800_000L;
        long days = ms / 86_400_000L;
        ms %= 86_400_000L;
        long hours = ms / 3_600_000L;
        ms %= 3_600_000L;
        long minutes = ms / 60_000L;
        ms %= 60_000L;
        long seconds = ms / 1000L;
        ms %= 1000L;
        return String.format("Time left: %d years, %d months, %d weeks, %d days, %02d:%02d:%02d.%03d",
                years, months, weeks, days, hours, minutes, seconds, ms);
    }
}

//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long now) {
//                Duration duration = Duration.between(start, Instant.now());
//                long ms = duration.toMillis();
//                long years = ms / (1000L * 60 * 60 * 24 * 365);
//                ms %= (1000L * 60 * 60 * 24 * 365);
//                long months = ms / (1000L * 60 * 60 * 24 * 30);
//                ms %= (1000L * 60 * 60 * 24 * 30);
//                long weeks = ms / (1000L * 60 * 60 * 24 * 7);
//                ms %= (1000L * 60 * 60 * 24 * 7);
//                long days = ms / (1000L * 60 * 60 * 24);
//                ms %= (1000L * 60 * 60 * 24);
//                long hours = ms / (1000L * 60 * 60);
//                ms %= (1000L * 60 * 60);
//                long minutes = ms / (1000L * 60);
//                ms %= (1000L * 60);
//                long seconds = ms / 1000;
//                ms %= 1000;
//                label.setText(String.format("Time since start: %d years, %d months, %d weeks, %d days, %02d:%02d:%02d.%03d",
//                        years, months, weeks, days, hours, minutes, seconds, ms));
//
//
//            }
//        };
//        timer.start();
//    }
//}
