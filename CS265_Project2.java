import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.text.Text;


/*
Purpose: Creates a interactive Investment Calculator through a GUI enviroment.
Author: Cole Holladay
Date: 10/13/19
*/

/*
Purpose: Creates the main stage, pane, and scene for the User Interface.
Precondition: All classes are imported above for the use of any JavaFX library. 
Postcondition: A Window is created with Labels, Textfields, and a button for 
               a user to interact with.
*/
public class CS265_Project2 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
    GridPane pane = new GridPane();
    pane.setAlignment(Pos.CENTER);
    pane.setPadding(new Insets(5,5,5,5));
    pane.setHgap(5.5);
    pane.setVgap(5.5);
    
    Label invLabel = new Label("Investment Amount:");
    pane.add(invLabel, 0,0);
    TextField investmentTextField = new TextField();
    investmentTextField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(investmentTextField, 1,0);
    
    Label yearLabel = new Label("Years:");
    pane.add(yearLabel,0,1);
    TextField yearTextField = new TextField();
    yearTextField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(yearTextField, 1,1);
    
    Label annualInterestRate = new Label("Annual Interest Rate:");
    pane.add(annualInterestRate, 0,2);
    TextField annIntRateTextField = new TextField();
    annIntRateTextField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(annIntRateTextField, 1,2);
    
    Label futValue = new Label("Future Value:");
    pane.add(futValue, 0,3);
    TextField futValueTextField = new TextField();
    futValueTextField.setAlignment(Pos.CENTER_RIGHT);
    pane.add(futValueTextField, 1,3);
    
    Button calBttn = new Button("Calculate");
    pane.add(calBttn, 1,4);
    GridPane.setHalignment(calBttn, HPos.RIGHT);
    
    calBttn.setOnAction(new EventHandler<ActionEvent>() {
        /*
        Purpose: Event that occurs when the user clicks the 'Calculate' button.
        Precondition: Button must be declared and iinstantiated before an action
                      can occur.
        Postcondition: Dependent on the input from a user: Two exceptions are avaible
                       if invalid values are inputted. 
        */
            @Override
            public void handle(ActionEvent e) {
                /*
                Values are assigned by their respected text fields. The correct exception is 
                thrown once the values are passed into the futureValue function.
                */
                try{
                    double investmentAmount = Double.parseDouble(investmentTextField.getText());
                    int year = Integer.parseInt(yearTextField.getText());
                    double interestRate = Double.parseDouble(annIntRateTextField.getText()) / 100;
                    
                    double futureValue = futureValue(investmentAmount, year, interestRate);
                    futValueTextField.setText(output(futureValue));
                }
                /*
                Purpose: Creates a new window with the correct InvalidInputException.
                Precondition: Invalid Input (Negative investment amount, year < 0, 
                              and interest rate < 0) must be entered for this exception
                              to occur. 
                Postcondition: A new window is made, listing the invalid input exception.
                */
                catch(InvalidInputException ex){
                    GridPane invalidPane = new GridPane();
                    invalidPane.setAlignment(Pos.CENTER);
                    
                    Text invalidText = new Text(ex.getException());
                    invalidPane.add(invalidText, 0,0);
                    
                    Scene invalidScene = new Scene(invalidPane, 300, 100);
                    Stage invalidStage = new Stage();
                    invalidStage.setScene(invalidScene);
                    invalidStage.show();
                }
                /*
                Purpose: Catches a NumberFormatException and sets the text
                         of the Future Value TextField to the String provided.
                Precondition: Input from either text field must be missing or 
                              not a real number for this event to occur.
                Postcondition: If exception occurs, the Future Value TextField
                               is set to the String below. 
                */
                catch(NumberFormatException ex){
                    futValueTextField.setText("Input Missing or Invalid.");
                }
            }
            /*
            Purpose: Actual function that calculates the futureValue of an investment. Throws
                    exceptions if the wrong input is provided.
            Precondition: Input from the user must be any real number and exist. If not, the
                          NumberFormatException is thrown. 
            Postcondition: * If investmentAmount is less than 0, than the respected InvalidInputException is thrown.
                           * If year is less than 1, then the respected InvalidInputException is thrown.
                           * If interest rate is less than or equal to 0, than the respoected InvalidInputExceptin is thrown.
                           * If all conditions are met, than the future value is calculated than returned to the function.
            */
            public double futureValue(double investmentAmount, int year,double interestRate)
                throws InvalidInputException{
                
                if (investmentAmount < 0){
                    throw new InvalidInputException("Investment amount must be equal or greater"
                            + " than zero.");
                }
                else if (year < 1){
                    throw new InvalidInputException("Year must be equal to or greater than 1.");
                }
                else if (interestRate <= 0){
                    throw new InvalidInputException("Interest Rate must be greater than zero.");
                }
                else{
                    double monthlyInterestRate = interestRate / 12;
                    double futureValue = investmentAmount * Math.pow(1 + monthlyInterestRate, year * 12);
                    return futureValue;
                }
            }
            /*
            Purpose: Outputs the futureValue to a String in the standarded monetary decimal format. 
            Precondition: No exceptions can be thrown: All conditions must be met. 
            Postcondition: futureValue is parsed to a String with 2 deciamsl places
                           and returned to the fucntion.
            */
            public String output(double futureValue){
                return String.format("%.2f", futureValue);
            }   
        });  
            
    Scene scene = new Scene(pane);
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
