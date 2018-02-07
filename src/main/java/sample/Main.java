package main.java.sample;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import main.java.sample.keeper.StringKeeper;
import main.java.sample.thread.TTWorker;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox root = new VBox();
        root.setSpacing(5);
        StringKeeper stringKeeper=new StringKeeper();


        TextArea textArea = new TextArea();
        textArea.setEditable(false);
        root.getChildren().add(textArea);
        Button btGetText=new Button("Get text");
        Service getterService = new Service() {
            @Override
            protected Task createTask() {
                Task<String> task=new Task<String>() {
                    @Override
                    protected String call() throws Exception {
                        return stringKeeper.getString();
                    }
                };
                task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        textArea.setText("get:"+task.getValue());
                    }
                });;
                return task;
            }
        };
        btGetText.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (getterService.isRunning())
                    return;
                textArea.setText("getting...");
                getterService.reset();
                getterService.start();

            }
        });
        root.getChildren().add(btGetText);
        Button btClear=new Button("Clear");
        root.getChildren().add(btClear);
        Service clearService=new Service() {
            @Override
            protected Task createTask() {
                Task task = new Task() {
                    @Override
                    protected Object call() throws Exception {
                        stringKeeper.clearBuffer();
                        return null;
                    }
                };
                task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                    @Override
                    public void handle(WorkerStateEvent event) {
                        textArea.setText("cleared!");
                    }
                });
                return task;
            }
        };
        btClear.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (clearService.isRunning())
                    return;
                textArea.setText("clearing...");
                clearService.reset();
                clearService.start();
            }
        });
        TTWorker ttWorker=new TTWorker(stringKeeper);
        ttWorker.startWork();
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.setMaximized(true);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
            System.out.print("close");
                ttWorker.stopWork();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
