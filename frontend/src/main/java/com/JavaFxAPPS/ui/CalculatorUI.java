package com.JavaFxAPPS.ui;

//import com.JavaFxAPPS.service.CalculatorService;
import com.JavaFxAPPS.JavaFxAPPS.CalculatorService;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;


@Component
public class CalculatorUI {

    private final CalculatorService calculatorService;
    private MFXTextField display;
    private String currentNumber = "";
    private String operator = "";
    private double firstNumber = 0;



    private Runnable showHomeCallback;

    public CalculatorUI(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public VBox createContent() {
        VBox root = new VBox(10);
        root.setPadding(new Insets(10));
        root.setAlignment(Pos.CENTER);



        display = new MFXTextField();
        display.setEditable(false);
        display.setStyle("-fx-font-size: 20px;");

        GridPane buttonGrid = createButtonGrid();
        MFXButton clearButton = createClearButton();


        MFXButton backButton = new MFXButton("Back");
        backButton.setOnAction(e -> {
            if (showHomeCallback != null) {
                showHomeCallback.run();
            }
            });

        root.getChildren().addAll(display, buttonGrid, clearButton, backButton);
        return root;
    }


    private GridPane createButtonGrid() {
        GridPane buttonGrid = new GridPane();
        buttonGrid.setHgap(5);
        buttonGrid.setVgap(5);

        String[][] buttonLabels = {
            {"7", "8", "9", "/"},
            {"4", "5", "6", "*"},
            {"1", "2", "3", "-"},
            {"0", ".", "=", "+"}
        };

        for (int i = 0; i < buttonLabels.length; i++) {
            for (int j = 0; j < buttonLabels[i].length; j++) {
                MFXButton button = new MFXButton(buttonLabels[i][j]);
                button.setMinSize(50, 50);
                button.setOnAction(e -> handleButton(button.getText()));
                buttonGrid.add(button, j, i);
            }
        }
        return buttonGrid;
    }

    private MFXButton createClearButton() {
        MFXButton clearButton = new MFXButton("C");
        clearButton.setMinSize(215, 50);
        clearButton.setOnAction(e -> clear());
        return clearButton;
    }

    private void handleButton(String value) {
        switch (value) {
            case "+":
            case "-":
            case "*":
            case "/":
                handleOperator(value);
                break;
            case "=":
                calculate();
                break;
            default:
                handleNumber(value);
                break;
        }
    }

    private void handleNumber(String value) {
        currentNumber += value;
        display.setText(currentNumber);
    }

    private void handleOperator(String op) {
        if (!currentNumber.isEmpty()) {
            firstNumber = Double.parseDouble(currentNumber);
            operator = op;
            currentNumber = "";
        }
    }

    private void calculate() {
        if (!currentNumber.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentNumber);
            double result = switch (operator) {
                case "+" -> calculatorService.add(firstNumber, secondNumber);
                case "-" -> calculatorService.subtract(firstNumber, secondNumber);
                case "*" -> calculatorService.multiply(firstNumber, secondNumber);
                case "/" -> calculatorService.divide(firstNumber, secondNumber);
                default -> 0.0;
            };
            display.setText(String.valueOf(result));
            currentNumber = "";
            operator = "";
        }
    }



    private void clear() {
        currentNumber = "";
        operator = "";
        firstNumber = 0;
        display.clear();
    }

    public void setShowHomeCallback(Runnable showHomeCallback) {
        this.showHomeCallback = showHomeCallback;
    }


}