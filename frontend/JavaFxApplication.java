package com.JavaFxAPPS;


import com.JavaFxAPPS.ui.CalculatorUI;
import com.JavaFxAPPS.JavaFxAPPS.CalculatorUI;
import com.JavaFxAPPS.JavaFxAPPS.CalculatorService;
import javafx.application.Application;
import javafx.scene.Scene;
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
        Scene scene = new Scene(calculatorUI.createContent(), 300, 400);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        springContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
