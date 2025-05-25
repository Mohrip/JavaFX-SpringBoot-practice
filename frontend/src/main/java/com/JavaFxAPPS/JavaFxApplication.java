package com.JavaFxAPPS;

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

        Button calcBtn = new Button("Calculator");
        Button todoBtn = new Button("Todo List");
        Button timeBtn = new Button("Time Counter");
        VBox root = new VBox(10, calcBtn, todoBtn, timeBtn);
        Scene scene = new Scene(root, 300, 400);
        calcBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, calculatorUI.createContent()));
        todoBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, todoUI));
        timeBtn.setOnAction(e -> root.getChildren().setAll(calcBtn, todoBtn, timeBtn, timeCounterUI));
        primaryStage.setTitle("JavaFX Spring App");
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
