import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

//Ricardo Salguero and Grant Osborn
public class GUI extends Application {

private GridModel pane1;
private GridModel pane2;
private GridModel pane3;
private final boolean[] gridAnswers1 = {false, false, true, false,
                                       false, true, false, false,
                                       false, false, false, true,
                                       true, false, false, false};

private final boolean[] gridAnswers2 = {true, false, false, false,
                                        false, false, false, true,
                                        false, true, false, false,
                                        false, false, true, false};

private final boolean[] gridAnswers3 = {false, false, true, false,
                                        false, false, false, true,
                                        true, false, false, false,
                                        false, true, false, false};
private final String[] topRowText = {"diamond ring", "earring", "gold chain",
        "wristwatch", "Addison Beach", "Burr Woods", "Front Beach",
        "Heffen Lane"};

private final String[] sideColText = {"$250", "$325", "$400", "$475", "Addison Beach",
        "Burr Woods", "Front Beach", "Heffen Lane"};

private final String Clues =
        "                                                       Clues \n" +
        "1. The wristwatch sold for 75 dollars less than the object found at Burr Woods.\n" +
        "2. The piece found at Heffen Lane was either the earring or the diamond ring.\n" +
        "3. The diamond ring sold for $325.\n" +
        "4. The piece found at Heffen Lane sold for somewhat less than the piece found at Addison Beach.\n" +
        "5. The item that sold for $325 was found at Addison Beach.";


    @Override
    public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("Logic Puzzle");

            Label label = new Label("hi");
            Label label1 = new Label("jason");
            Label label4 = new Label("jason");
            Label label3 = new Label("jason");

            label.setStyle("-fx-rotate: 90deg");
            label1.setStyle("-fx-rotate: 90deg");


            GridModel gridPane1 = createGrid(gridAnswers2);
            GridModel gridPane2 = createGrid(gridAnswers2);
            GridModel gridPane3 = createGrid(gridAnswers2);

        
            //create bottom buttons
            Button hint = new Button("hint");
            Button clearErr = new Button("clear errors");
            Button reset = new Button("start over");
            clearErr.setOnAction(e ->{
                gridPane1.clearErrors();
                gridPane2.clearErrors();
                gridPane3.clearErrors();
            });
        
            reset.setOnAction(e ->{
                gridPane1.startOver();
                gridPane2.startOver();
                gridPane3.startOver();
            });

            HintGen h = new HintGen();
            hint.setOnAction(e->{
               System.out.println(h.generate(gridPane1, gridPane2, gridPane3));
            });

            GridPane mainPane = new GridPane();

            //creates the text on top of the grid
            GridPane topText = createTopText(topRowText);

            //creates the text on side of the grid
            GridPane sideText = createSideText(sideColText);

            //Text for the clues
            TextFlow clues = new TextFlow(new Text(Clues.toString()));
            clues.setPadding(new Insets(0,0,0,70));

            //The main layout where every item is place in a column, row index
            mainPane.setPadding(new Insets (50,5,0,10));
            mainPane.setHgap(0);
            mainPane.setVgap(0);
            mainPane.add(topText, 1 ,0, 2, 1);
            mainPane.add(sideText, 0 ,1, 1, 2);
            mainPane.add(clues, 7, 1, 1, 2);
            mainPane.add(gridPane1, 1 ,1);
            mainPane.add(gridPane2, 2 ,1);
            mainPane.add(gridPane3, 1 ,2);
            mainPane.add(hint, 3 ,3);
            mainPane.add(clearErr, 4 ,3);
            mainPane.add(reset, 5 ,3);


            Scene scene = new Scene(mainPane, 2000, 1000);
            //save the instance of GridModels to be accessed later to check answers
            this.pane1 = gridPane1;
            this.pane2 = gridPane1;
            this.pane3 = gridPane1;
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    private GridPane createTopText(String[] topRowText) {
        GridPane topText = new GridPane();
        topText.setPadding(new Insets(0,0,0,0));
        topText.setHgap(0.0);
        topText.setStyle("-fx-min-height: 90px; -fx-max-width: 600px");

        //add label from the text array
        for (int i = 0; i < topRowText.length; i++) {
            Label label = new Label(topRowText[i]);
            label.setStyle("-fx-wrap-text: true; -fx-rotate: -90px; -fx-min-width: 75px; -fx-min-height: 75px;" +
                    "-fx-underline: true; -fx-font-weight: bold; -fx-alignment: center-left");
            topText.add(label, i, 0);
        }

        return topText;
    }

    private GridPane createSideText(String[] sideColText) {
        GridPane sideText = new GridPane();
        sideText.setPadding(new Insets(0,0,0,0));
        sideText.setVgap(0.0);
        sideText.setStyle("-fx-min-height: 90px;");

        //add Labels from the text array
        for (int i = 0; i < sideColText.length; i++) {
            Label label = new Label(sideColText[i]);
            label.setStyle("-fx-font-weight: bold; -fx-min-width: 75px; -fx-min-height: 75px; -fx-alignment: center-right");
            sideText.add(label, 0, i);
        }

        return sideText;
    }

    //Method will be called by one of the GridModels and will check if all 3 Grids are correct
    public boolean checkCorrectAnswers(){
        int i = 0;
        while(i < 16) {
            if(((GridButton)pane1.getChildren().get(i)).isMarkedCorrect() == pane1.getGridAnswers()[i]){
                i++;
            }
            else{
                break;
            }
            if(((GridButton)pane2.getChildren().get(i)).isMarkedCorrect() == pane2.getGridAnswers()[i]){
                i++;
            }
            else{
                break;
            }
            if(((GridButton)pane3.getChildren().get(i)).isMarkedCorrect() == pane3.getGridAnswers()[i]){
                i++;
            }
            else{
                break;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You Won!");
            alert.setHeaderText(null);
            alert.setContentText("I have a great message for you!");

            alert.showAndWait();
            return true;
        }
        return false;
    }

    //Method will create 3 GridModels and add buttons with events
    public GridModel createGrid(boolean[] list){
        GridModel gridPane = new GridModel(list);
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                final int row = r;
                final int column = c;
                GridButton button = new GridButton();
                button.setOnMouseEntered(event -> button.setEffect(new Bloom()));
                button.setOnMouseExited(event -> button.setEffect(null));
                button.setOnAction(event -> {
                    if(gridPane.checkViolations(column, row)){
                        button.changeState();
                        if(button.isMarkedCorrect()){
                            gridPane.setRowAndColumnToX(column,row);
                        }else if(button.isClear()){
                            gridPane.clearRowAndColumn(column, row);
                        }
                    }//end if violations
                    this.checkCorrectAnswers();
            });
                gridPane.add(button, c, r);
            }
        }
        return gridPane;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
