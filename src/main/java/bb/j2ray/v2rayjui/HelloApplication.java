package bb.j2ray.v2rayjui;

import bb.j2ray.config.IParseConfig;
import bb.j2ray.config.V2RayConfig;
import bb.j2ray.parse.ClashParse;
import bb.j2ray.parse.FileClashParse;
import bb.j2ray.parse.URLClashParse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

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

        initGenerateConfig(side,stackPane);


        root.setLeft(side);
        root.setCenter(stackPane);
        Scene scene = new Scene(root, screenWidth, screenHeight);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }


    private void initGenerateConfig(VBox side,StackPane stackPane){
        Button button=new Button("GenerateConfig");
        TextArea logTextArea = new TextArea();
        BorderPane bp=new BorderPane();
        ScrollPane scrollPane = new ScrollPane(logTextArea);
        scrollPane.setFitToWidth(true);  // 设置 ScrollPane 宽度适应
        scrollPane.setFitToHeight(true); // 设置 ScrollPane 高度适应
        side.getChildren().add(button);
        bp.setCenter(scrollPane);
        TextField textField=new TextField();
        textField.setText("please input the url or yaml file path in this field");
        bp.setTop(textField);

        stackPane.getChildren().add(bp);
        button.setOnAction(actionEvent -> generateByURL(textField.getText().trim(), logTextArea));
    }
    private void generateByURL(String ur,TextArea tx){
        IParseConfig ipc;
        if(ur.startsWith("http://")||ur.startsWith("https://")){
            ipc=new URLClashParse();
        }else{
            ipc=new FileClashParse();
        }

        String cont= null;
        V2RayConfig vc= null;
        try {
            vc = ipc.parse(ur);
            cont= JSON.toJSONString(vc, JSONWriter.Feature.PrettyFormat);
        } catch (Exception e) {
            cont=exceptionStack(e);
        }
        tx.setText(cont);
    }


    public static String exceptionStack(Throwable e) {
        if (e == null){
            return "";
        }
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
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