import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class GUI extends Application {

private GridPane pane;
private boolean[] gridAnswers = {false, true, false, false,
                            true, false, false, false,
                            false, false, true, false,
                            false, false, false, false};
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("GridPane Experiment");



        GridModel gridPane1 = createGrid(gridAnswers);
        GridModel gridPane2 = createGrid(gridAnswers);

        HBox hBox = new HBox();

        hBox.setSpacing(0);
        hBox.setPadding(new Insets(5,5,5,5));
        hBox.getChildren().addAll(gridPane1,gridPane2);


        Scene scene = new Scene(hBox, 1000, 1000);
        pane = gridPane1;
        primaryStage.setScene(scene);
        primaryStage.show();
    }

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
