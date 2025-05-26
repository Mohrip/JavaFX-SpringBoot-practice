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
import com.JavaFxAPPS.ui.QuoteController;

@SpringBootApplication
public class JavaFxApplication extends Application {
    private ConfigurableApplicationContext springContext;

    private Button calcBtn;
    private Button todoBtn;
    private Button timeBtn;
    private Button hardwareBtn;
    private Button quoteBtn;


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

        calcBtn = new Button("Calculator");
        todoBtn = new Button("Todo List");
        timeBtn = new Button("Time Counter");
        hardwareBtn = new Button("Hardware Usage");
        quoteBtn = new Button("Quote");
        VBox root = new VBox(10, calcBtn, todoBtn, timeBtn, hardwareBtn, quoteBtn);

        final Parent[] quoteRoot = new Parent[1];
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/JavaFxAPPS/ui/QuoteView.fxml"));
            loader.setControllerFactory(springContext::getBean);
            quoteRoot[0] = loader.load();
            //Parent quoteView = loader.load();
            QuoteController controller = loader.getController();
            controller.setShowHomeCallback(() -> showHome(root));
        } catch (IOException e) {
            e.printStackTrace();

            // Optionally show an error dialog or fallback UI
        }
        todoUI.setShowHomeCallback(() -> showHome((root)));
        calculatorUI.setShowHomeCallback(() -> showHome((root)));
        hardwareUsageUI.setShowHomeCallback(() -> showHome((root)));
        timeCounterUI.setShowHomeCallback(() -> showHome((root)));



//        Button calcBtn = new Button("Calculator");
//        Button todoBtn = new Button("Todo List");
//        Button timeBtn = new Button("Time Counter");
//        Button hardwareBtn = new Button("Hardware Usage");
//        Button quoteBtn = new Button("Quote");
//        calcBtn = new Button("Calculator");
//        todoBtn = new Button("Todo List");
//        timeBtn = new Button("Time Counter");
//        hardwareBtn = new Button("Hardware Usage");
//        quoteBtn = new Button("Quote");
       // VBox root = new VBox(10, calcBtn, todoBtn, timeBtn, hardwareBtn, quoteBtn);
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


    public void showHome(VBox root) {
        root.getChildren().setAll(calcBtn, todoBtn, timeBtn, hardwareBtn, quoteBtn);
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
