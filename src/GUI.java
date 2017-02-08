import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.table.TableColumn;

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

        GridModel gridPane1 = createGrid(gridAnswers);
        GridModel gridPane2 = createGrid(gridAnswers);
        GridModel gridPane3 = createGrid(gridAnswers);

        HBox hBox = new HBox();
        HBox textBox = new HBox();
        VBox vBox = new VBox();
        textBox.setSpacing(0);
        textBox.setPadding(new Insets(5,5,5,5));
        textBox.getChildren().addAll(label);

        vBox.setSpacing(0);
        vBox.setPadding(new Insets (50,5,0,10));

        hBox.setSpacing(0);
        hBox.setPadding(new Insets(5,5,0,0));
        hBox.getChildren().addAll(gridPane1,gridPane2);

        vBox.getChildren().addAll(textBox, hBox, gridPane3);

        Scene scene = new Scene(vBox, 1000, 1000);
        pane = gridPane1;
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
