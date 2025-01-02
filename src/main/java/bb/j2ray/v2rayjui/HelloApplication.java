package bb.j2ray.v2rayjui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Screen primaryScreen = Screen.getPrimary();
        Rectangle2D screenBounds = primaryScreen.getVisualBounds();
        double screenWidth = screenBounds.getWidth()/2;
        double screenHeight = screenBounds.getHeight()/2;

        BorderPane root=new BorderPane();
        VBox side=new VBox();
        StackPane stackPane=new StackPane();

        initLog(side,stackPane);


        root.setLeft(side);
        root.setCenter(stackPane);
        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    private void initLog(VBox side,StackPane stackPane){
        Button button=new Button("log");
        TextArea logTextArea = new TextArea();
        logTextArea.setEditable(false);  // 设置为只读，防止编辑
        logTextArea.setWrapText(true);   // 设置文本自动换行

        logTextArea.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-font-family: 'Courier New';");

        ScrollPane scrollPane = new ScrollPane(logTextArea);
        scrollPane.setFitToWidth(true);  // 设置 ScrollPane 宽度适应
        scrollPane.setFitToHeight(true); // 设置 ScrollPane 高度适应

        scrollPane.setStyle("-fx-background-color: black;");
        side.getChildren().add(button);
        stackPane.getChildren().add(scrollPane);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
    }


    public static void main(String[] args) {
        launch();
    }
}