package com.JavaFxAPPS.ui;

import com.JavaFxAPPS.JavaFxAPPS.QuoteService;
import  javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QuoteController {
    @FXML
    private Label quoteLabel;

    private final QuoteService quoteService;
    private final StringProperty quote = new SimpleStringProperty("Click to get a quote!");

    @Autowired
    public QuoteController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @FXML
    public void initialize() {
        quoteLabel.textProperty().bind(quote);

    }

    @FXML
    public void onGetQuote() {
        new Thread(() -> {
            String newQuote = quoteService.getRandomQuote();
            Platform.runLater(() -> quote.set(newQuote));
        }).start();
    }

    private Runnable showHomeCallback;

    public void setShowHomeCallback(Runnable callback) {
        this.showHomeCallback = callback;
    }

    @FXML
    public void onBack() {
        if (showHomeCallback != null) {
            showHomeCallback.run();
        }
    }

}
