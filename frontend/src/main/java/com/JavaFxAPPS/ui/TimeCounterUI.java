package com.JavaFxAPPS.ui;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;


@Component
public class TimeCounterUI extends VBox {

    private final Label countdownLabel = new Label("Set your timer");
    private AnimationTimer timer;
    private long remainingMs = 0;
    private Runnable showHomeCallback;

    public TimeCounterUI() {
        setPadding(new Insets(20));
        setSpacing(20);
        setAlignment(Pos.CENTER);
        getStyleClass().add("root");

        Label title = new Label("Countdown Timer");
        title.getStyleClass().add("title-label");
        countdownLabel.getStyleClass().add("countdown-label");

        TextField[] fields = new TextField[8];
        String[] units = {"Years", "Months", "Weeks", "Days", "Hours", "Minutes", "Seconds", "Milliseconds"};

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        for (int i = 0; i < 8; i++) {
            fields[i] = new TextField();
            fields[i].setPromptText("0");
            fields[i].getStyleClass().add("text-field");
            fields[i].setMaxWidth(Double.MAX_VALUE);


            Label lbl = new Label(units[i]);
            lbl.getStyleClass().add("label");

            VBox box = new VBox(5, lbl, fields[i]);
            box.setFillWidth(true);
            GridPane.setHgrow(box, Priority.ALWAYS);
            grid.add(box, i % 4, i / 4);
        }

        for (int i =0; i < 8; i++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setHgrow(Priority.ALWAYS);
            cc.setPercentWidth(25);
            grid.getColumnConstraints().add(cc);
        }

        Button startBtn = new Button("Start Countdown");
        Button stopBtn = new Button("Stop Countdown");
        Button backBtn = new Button("Back");

        stopBtn.setMaxWidth(Double.MAX_VALUE);
        startBtn.setMaxWidth(Double.MAX_VALUE);
        backBtn.setMaxWidth(Double.MAX_VALUE);

        startBtn.setOnAction(e -> startCountdown(fields));



        stopBtn.setOnAction(e -> {
            if (timer != null) {
                timer.stop();
                countdownLabel.setText("Countdown stopped.");
            }
        });


        backBtn.setOnAction(e -> {
            if (showHomeCallback != null) showHomeCallback.run();
        }
         );
        // test

        HBox buttonBox = new HBox(15, startBtn, stopBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setFillHeight(true);
        HBox.setHgrow(startBtn, Priority.ALWAYS);
        HBox.setHgrow(stopBtn, Priority.ALWAYS);
        HBox.setHgrow(backBtn, Priority.ALWAYS);

        VBox content = new VBox(20, title, grid, buttonBox, countdownLabel);
        content.setAlignment(Pos.CENTER);
        content.setFillWidth(true);
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        getChildren().add(scrollPane);
        var css = getClass().getResource("/com/JavaFxAPPS/ui/timer-style.css");
        if (css != null) {
            getStylesheets().add(css.toExternalForm());
        }


        getChildren().addAll(title, grid, buttonBox, countdownLabel);
       getStylesheets().add(getClass().getResource("/com/JavaFxAPPS/ui/timer-style.css").toExternalForm());    }

    private void startCountdown(TextField[] fields) {
        long[] units = new long[8];
        for (int i = 0; i < fields.length; i++) {
            units[i] = parseLong(fields[i].getText());
        }

        remainingMs =
                units[7] +
                        units[6] * 1000L +
                        units[5] * 60_000L +
                        units[4] * 3_600_000L +
                        units[3] * 86_400_000L +
                        units[2] * 604_800_000L +
                        units[1] * 2_592_000_000L +
                        units[0] * 31_536_000_000L;

        if (remainingMs <= 0) {
            countdownLabel.setText("Please enter a positive duration.");
            return;
        }

        long endTime = System.currentTimeMillis() + remainingMs;

        if (timer != null) timer.stop();

        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long msLeft = endTime - System.currentTimeMillis();
                if (msLeft <= 0) {
                    countdownLabel.setText("Countdown finished!");
                    stop();
                } else {
                    countdownLabel.setText(formatDuration(msLeft));
                }
            }
        };
        timer.start();
    }


    private long parseLong(String s) {
        try {
            return Long.parseLong(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private String formatDuration(long ms) {
        long years = ms / 31_536_000_000L; ms %= 31_536_000_000L;
        long months = ms / 2_592_000_000L; ms %= 2_592_000_000L;
        long weeks = ms / 604_800_000L; ms %= 604_800_000L;
        long days = ms / 86_400_000L; ms %= 86_400_000L;
        long hours = ms / 3_600_000L; ms %= 3_600_000L;
        long minutes = ms / 60_000L; ms %= 60_000L;
        long seconds = ms / 1000L;

        return String.format(
                "%02dy : %02dm : %02dw : %02dd : %02dh : %02dm : %02ds",
                years, months, weeks, days, hours, minutes, seconds
        );
    }

    public void setShowHomeCallback(Runnable showHomeCallback) {
        this.showHomeCallback = showHomeCallback;
    }
}
