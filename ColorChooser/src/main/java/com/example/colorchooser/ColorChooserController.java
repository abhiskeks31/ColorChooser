package com.example.colorchooser;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ColorChooserController {
    @FXML private Slider redSlider;
    @FXML private Slider greenSlider;
    @FXML private Slider blueSlider;
    @FXML private Slider alphaSlider;
    @FXML private TextField redTextField;
    @FXML private TextField greenTextField;
    @FXML private TextField blueTextField;
    @FXML private TextField alphaTextField;
    @FXML private Rectangle colorRectangle;

    private IntegerProperty red = new SimpleIntegerProperty();
    private IntegerProperty green = new SimpleIntegerProperty();
    private IntegerProperty blue = new SimpleIntegerProperty();
    private DoubleProperty alpha = new SimpleDoubleProperty();

    public void initialize() {
        bindBidirectional(redSlider, redTextField, red);
        bindBidirectional(greenSlider, greenTextField, green);
        bindBidirectional(blueSlider, blueTextField, blue);
        bindBidirectional(alphaSlider, alphaTextField, alpha);

        colorRectangle.fillProperty().bind(colorProperty());
    }

    private void bindBidirectional(Slider slider, TextField textField, IntegerProperty property) {
        // Bind slider value to property
        property.bindBidirectional(slider.valueProperty());
        // Bind textField text to property
        textField.textProperty().bindBidirectional(property, java.text.NumberFormat.getIntegerInstance());
    }

    private void bindBidirectional(Slider slider, TextField textField, DoubleProperty property) {
        // Bind slider value to property
        property.bindBidirectional(slider.valueProperty());
        // Bind textField text to property
        textField.textProperty().bindBidirectional(property, java.text.NumberFormat.getNumberInstance());
    }

    private ObjectBinding<Color> colorProperty() {
        return Bindings.createObjectBinding(() ->
                Color.rgb(red.get(), green.get(), blue.get(), alpha.get()), red, green, blue, alpha);
    }

}
