package bb.j2ray.v2rayjui;

import bb.j2ray.boot.V2RayBoot;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        V2RayBoot.start();
    }
}