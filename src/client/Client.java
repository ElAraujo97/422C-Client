package client;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.javafx.scene.control.behavior.PaginationBehavior;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Client extends Application{
  private List<Message> myItems;
  private List<User> myUsers;
  private static String host = "127.0.0.1";
  private BufferedReader fromServer;
  private PrintWriter toServer;
  //private Scanner consoleInput = new Scanner(System.in);

  public Client()  {
	// TODO Auto-generated constructor stub
	  
  };
  

//Each time we get a new client, setup networking thread
public static void main(String[] args) {
	  launch(args); 
  
}
  

	//Setup threads and read messages from server
  private void setUpNetworking() throws Exception {
    @SuppressWarnings("resource")
    Socket socket = new Socket(host, 4242);
    System.out.println("Connecting to ... " + socket);
    fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    toServer = new PrintWriter(socket.getOutputStream());

    Thread readerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        String input;
        try {
          while ((input = fromServer.readLine()) != null) {
            System.out.println("From server: " + input);
            processRequest(input);
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });

    
    
    
    Thread writerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        while (true) {
          //String input = consoleInput.nextLine();
          //String[] variables = input.split(",");
          
          
          //Message request = new Message(variables[0], variables[1], Integer.valueOf(variables[2]));
          
          GsonBuilder builder = new GsonBuilder();
          Gson gson = builder.create();
          //sendToServer(gson.toJson(request));
        }
      }
    });

    readerThread.start();
    writerThread.start();
  }

  //Implement this with whatever the server sends
  protected void processRequest(String input) {
	  
	  String[] variables = input.split(",");
	  Message myMessage = new Message(variables[0], Integer.valueOf(variables[1]),Integer.valueOf(variables[2]), variables[3],Integer.valueOf(variables[4]), Boolean.parseBoolean(variables[5]), variables[6]);
	  String action = myMessage.getAction();
	  switch (action) {
	case "Setup":
		myMessage.setAction("Nothing");
		this.myItems = new ArrayList<Message>();
		myItems.add(myMessage);
		break;
	case "Buy":
		break;

	case "Bid":
		break;
		
		
	
	case "TimeOut":
		break;
	
	//Continue without any action
	case "Nothing":
		break;
	}
	  
	  
	  
	  return;
  }

  //Send strings to server
  protected void sendToServer(String string) {
    System.out.println("Sending to server: " + string);
    toServer.println(string);
    toServer.flush();
  }


@Override
public void start(Stage primaryStage) throws Exception {
	// TODO Auto-generated method stub
	try {
	      new Client().setUpNetworking();
	     
	      
	    //Setup first stage that has login screen  
	    BorderPane pane = new BorderPane();
	    pane.setPadding(new Insets(10,10,10,10));
	    VBox loginBox = new VBox();
	    Button loginButton = new Button("Login");
	    Button guestButton = new Button("Guest");
	    Button newButton = new Button("New User");
	    TextField username = new TextField();
	    username.setPromptText("Username");
	    PasswordField password = new PasswordField();
	    password.setPromptText("Password");
	    myUsers = new ArrayList<User>();
	    Label welcomeLabel = new Label("Welcome!");
	    
	    loginBox.getChildren().addAll(welcomeLabel,password, loginButton, guestButton,newButton);
	    loginBox.setAlignment(Pos.CENTER);
	    
	    
	    pane.setCenter(loginBox);
	    
	    
	    
	  	Scene scene = new Scene(pane, 300, 300);
	  	primaryStage.setTitle("Login Screen");
	  	primaryStage.setScene(scene);
	  	primaryStage.show();
	  	
	  	
	  	//Setup for auction screen
	  	HBox myBox = new HBox();
	  	myBox.setPadding(new Insets(10,10,10,10));
	  	Scene auction = new Scene(myBox, 300,300);
	  	
	  	
	  	
	  	
	  	
	  	
	  	
	  	newButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				User myUser = new User(username.getText(), password.getText());
				myUsers.add(myUser);
				System.out.println("new user added");
				
			}
			
		});
	  	
	  	
	  	guestButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				User myUser = new User();
				myUsers.add(myUser);
				System.out.println("guest added");
				
			}
		
	  	
	  	});
	  	
	  	loginButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				for(int i = 0; i < myUsers.size(); i++) {
					if(myUsers.get(i).getName() == username.getText() && myUsers.get(i).getPassword() == password.getText()) {
							System.out.println("Success!");
							
							
							
							
							
						}
					}
					
				System.out.println("Failure");
				}
				
				
			
		
	  	});
	  	
	  	
	  	
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	
	
	
	
	
	
	
}


}