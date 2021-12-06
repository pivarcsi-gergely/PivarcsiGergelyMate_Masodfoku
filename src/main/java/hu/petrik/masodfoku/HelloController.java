package hu.petrik.masodfoku;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Timer;
import java.util.TimerTask;

public class HelloController {
    @FXML
    private Label elsoEredmenyLabel, masodikEredmenyLabel;
    @FXML
    private Button szamolGomb;
    @FXML
    private TextField aSzamInput, bSzamInput, cSzamInput;

    private double a, b, c;

    public void szamol() {
        try {
            a = Double.parseDouble(aSzamInput.getText().trim());
            b = Double.parseDouble(bSzamInput.getText().trim());
            c = Double.parseDouble(cSzamInput.getText().trim());
        } catch (NumberFormatException e) {
            Alert felugroAblak = new Alert(Alert.AlertType.ERROR, e.getMessage());
            felugroAblak.show();
        }

        Timer egyetlenTimer = new Timer();
        TimerTask egyetlenTTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if (aSzamInput == null || bSzamInput == null || cSzamInput == null) {
                        Alert felugroAblak2 = new Alert(Alert.AlertType.WARNING, "Minden mező kitöltése kötelező");
                        felugroAblak2.show();
                    } else if (a < 0 || b < 0 || c < 0) {
                        Alert felugroAblak3 = new Alert(Alert.AlertType.ERROR, "Nem oldható meg a valós számok halmazán");
                        felugroAblak3.show();
                    }
                    /*else if (a == 0 || b == 0 || c == 0) {
                        elsoEredmenyLabel.setText("0");
                        masodikEredmenyLabel.setText("");
                    }*/
                    else {
                        //String eredmeny1 = elsoEredmenyLabel.getText();
                        //String eredmeny2 = masodikEredmenyLabel.getText();
                        double diszkriminans = (b * b) - (4 * a * c);
                        double megoldoKeplet1 = (-b + Math.sqrt(diszkriminans) )/ (2 * a);
                        double megoldoKeplet2 = (-b - Math.sqrt(diszkriminans) )/ (2 * a);
                        System.out.println(megoldoKeplet1);
                        elsoEredmenyLabel.setText("x1=" + megoldoKeplet1);
                        masodikEredmenyLabel.setText("x2=" + megoldoKeplet2);
                    }
                });
            }
        };
        egyetlenTimer.scheduleAtFixedRate(egyetlenTTask, 0, 1);
        aSzamInput.setText("");
        bSzamInput.setText("");
        cSzamInput.setText("");
    }
}