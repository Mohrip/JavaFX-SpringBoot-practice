package com.JavaFxAPPS;

import com.JavaFxAPPS.ui.HardwareUsageUI;
import com.JavaFxAPPS.ui.TimeCounterUI;
import com.JavaFxAPPS.ui.CalculatorUI;
import com.JavaFxAPPS.ui.TodoUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import java.io.IOException;

@SpringBootApplication
public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext springContext;

    @Override
    public void init() {
        springContext = SpringApplication.run(JavaFxApplication.class);
    }

    @Override
    public void start(Stage primaryStage) {
        CalculatorUI calculatorUI = springContext.getBean(CalculatorUI.class);
        TodoUI todoUI = springContext.getBean(TodoUI.class);
        TimeCounterUI timeCounterUI = springContext.getBean(TimeCounterUI.class);
        HardwareUsageUI hardwareUsageUI = springContext.getBean(HardwareUsageUI.class);

        final Parent[] quoteRoot = new Parent[1];
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/JavaFxAPPS/ui/QuoteView.fxml"));
            loader.setControllerFactory(springContext::getBean);
            quoteRoot[0] = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            // Optionally show an error dialog or fallback UI
        }


        Button calcBtn = new Button("Calculator");
        Button todoBtn = new Button("Todo List");
        Button timeBtn = new Button("Time Counter");
        Button hardwareBtn = new Button("Hardware Usage");
        Button quoteBtn = new Button("Quote");
        VBox root = new VBox(10, calcBtn, todoBtn, timeBtn, hardwareBtn, quoteBtn);
        Scene scene = new Scene(root, 300, 400);
        calcBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, calculatorUI.createContent()));
        todoBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, todoUI));
        timeBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, timeCounterUI));
        hardwareBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, hardwareUsageUI));
        quoteBtn.setOnAction(e -> {
            if (quoteRoot[0] != null) {
                root.getChildren().setAll(calcBtn, todoBtn, timeBtn, hardwareBtn, quoteBtn, quoteRoot[0]);
            }
        });        primaryStage.setTitle("JavaFX Spring App");
        primaryStage.setScene(scene);
        primaryStage.show();

//        Scene scene = new Scene(calculatorUI.createContent(), 300, 400);
//        primaryStage.setTitle("Calculator");
//        primaryStage.setScene(scene);
//        primaryStage.show();

    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
