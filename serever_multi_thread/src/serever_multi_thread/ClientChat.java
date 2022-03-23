package serever_multi_thread;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class ClientChat extends Application{
	
 public static void main(String[] arg) {
	 launch(arg);
 }

@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	primaryStage.setTitle("Client chat");
	BorderPane B1 = new BorderPane();
	
	Label     labebHost = new Label("Host : ");
	TextField text1     = new TextField("localhost");
	
	Label     labebPort = new Label("Port : ");
	TextField text2     = new TextField("3456");
	
	Button    Butt1     = new Button("connecter");
	
	HBox   hbox = new HBox(); hbox.setSpacing(10); hbox.setPadding(new Insets(10));
	hbox.getChildren().addAll(labebHost,text1,labebPort,text2,Butt1);
	
	B1.setTop(hbox);
	
	Scene scene = new Scene(B1,500,400);
	primaryStage.show();
}
}
