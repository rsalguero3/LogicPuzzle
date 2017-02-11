import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.*;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

//Ricardo Salguero
public class GUI extends Application {

private GridPane pane;
private boolean[] gridAnswers = {false, true, false, false,
                            true, false, false, false,
                            false, false, true, false,
                            false, false, false, false};
    @Override
    public void start(Stage primaryStage) throws Exception {
            primaryStage.setTitle("GridPane Experiment");

            Label label = new Label("hi");
            Label label1 = new Label("jason");
            label.setStyle("-fx-rotate: 90deg; -fx-border-style: solid");
            label1.setStyle("-fx-rotate: 90deg");

            GridModel gridPane1 = createGrid(gridAnswers);
            GridModel gridPane2 = createGrid(gridAnswers);
            GridModel gridPane3 = createGrid(gridAnswers);

            HBox hBox = new HBox();
            HBox hBox1 = new HBox();
            GridPane pane = new GridPane();
            GridPane mainPane = new GridPane();

            //creates the text on top of the grid
            pane.setPadding(new Insets(0,0,15,60));
            pane.setHgap(60.0);
            pane.add(label, 0, 0);
            pane.add(label1, 1, 0);

            //The main layout where every item is place in a column, row index
            mainPane.setPadding(new Insets (50,5,0,10));mainPane.setHgap(0);
            mainPane.setVgap(0);
            mainPane.add(pane, 0 ,0, 2, 1);
            mainPane.add(gridPane1, 1 ,1);
            mainPane.add(gridPane2, 2 ,1);
            mainPane.add(gridPane3, 1 ,2);

            Scene scene = new Scene(mainPane, 1000, 1000);
            this.pane = gridPane1;
            primaryStage.setScene(scene);
            primaryStage.show();
    }

    //Method will be called by one of the GridModels and will check if all 3 Grids are correct
    public boolean checkCorrectAnswers(GridModel gridPane){
        int i = 0;
        while(i < 16) {
            if(((GridButton)pane.getChildren().get(i)).isMarkedCorrect() == gridPane.getGridAnswers()[i]){
                i++;
            }
            else{
                break;
            }
            return true;
        }
        return false;
    }

    //Method will create 3 GridModels and add buttons with events
    public GridModel createGrid(boolean[] list){
        GridModel gridPane = new GridModel(list);
        for (int r = 0; r < 4; r++) {
            for (int c = 0; c < 4; c++) {
                GridButton button = new GridButton();
                button.setOnMouseEntered(event -> button.setEffect(new Bloom()));
                button.setOnMouseExited(event -> button.setEffect(null));
                button.setOnAction(event -> {
                    button.changeState();
                    this.checkCorrectAnswers(gridPane);
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
