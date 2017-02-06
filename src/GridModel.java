import javafx.scene.layout.GridPane;

public class GridModel extends GridPane{

    private GridPane pane;
    private boolean[] gridAnswers = {};

    public GridModel(boolean[] list){
        super();
        gridAnswers = list;

    }

    public boolean[] getGridAnswers() {
        return gridAnswers;
    }
}
