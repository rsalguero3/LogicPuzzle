
import java.util.ArrayList;
import javafx.scene.effect.Bloom;
import javafx.scene.layout.Background;

/**
 *
 * @author Grant Osborn
 * 
 * 
 */
public class HintGen {

     private ArrayList<Hint> hints;
    
    public HintGen(){
        hints= new ArrayList<>();
        hints.add(new Hint(0,1,0,"Diamond ring and $325 are explicitly true. Mark the highlighted cell as TRUE\n"));
        hints.add(new Hint(0,1,2,"Addison beach and $325 are explicitly true. Mark the highlighted cell as TRUE\n"));
        hints.add(new Hint(1,3,1,"We know the diamond ring was found at addison, so the earring must have been "
                + "found at Heffen Lane. Mark the highlighted cell as true.\n"));
        
        hints.add(new Hint(1,0,0,"the piece at Heffen lane sold for less than the $325 diamond ring "
                + "because we know the earring was found at Heffen Lane and the only thing less than $325 is $250 "
                + "the earring must have sold for $250. Mark the highlighted cell as TRUE \n"));
        
        hints.add(new Hint(3,3,0,"The wristwatch sold for less than the object found at Burr Woods "
                + "so the wrist watch can not be the most expensive. Mark the highlighted cell as FALSE \n"));
        
        hints.add(new Hint(2,3,0,"By process of elimination we know the "
                + "gold chain cost $475. Mark the highlighted cell as TRUE \n"));
        
        hints.add(new Hint(3,2,0,"By process of elimination we know the "
                + "wristwatch cost $400. Mark the highlighted cell as TRUE \n"));
        
        hints.add(new Hint(3,2,0,"By process of elimination we know the "
                + "wristwatch cost $400. Mark the highlighted cell as TRUE \n"));
        
         hints.add(new Hint(2,1,1,"The wristwatch sold for $75 less than the object found at "
                 + "Burr Woods. the gold chain cost $75 more than the wristwatch so it must have been found "
                 + "at Burr Woods. Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(0,0,1,"We know the diamond sold for $325 and the "
                 + "item that sold for $325 was found at Addison Beach. Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(3,2,1,"By process of elimination we know the "
                + "wristwatch was found at Front Beach . Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(3,0,2,"We know the earring cost $250 and was found at Heffen Lane "
                 + "  Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(2,2,2,"We know the wristwatch cost $400 and was found at Front Beach"
                 + "  Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(1,3,2,"By process of elimination we know the "
                + "object that cost $475  was found at Burr Woods . Mark the highlighted cell as TRUE \n"));
    }   
    
    public String generate(GridModel g1,GridModel g2,GridModel g3){
        Hint myHint = getHint(g1, g2, g3);
            switch (myHint.getGridNum()) {
                //error case
                case-1:
                    break;
                case 0:
                    g1.getButtonAt(myHint.getX(),myHint.getY()).setStyle("-fx-min-height: 75px; -fx-min-width: 75px; "
                                                                         + "-fx-background-color: #e5ff00;");
                    break;
                case 1:
                    g3.getButtonAt(myHint.getX(),myHint.getY()).setStyle("-fx-min-height: 75px; -fx-min-width: 75px; "
                                                                        + "-fx-background-color: #e5ff00;");
                    break;
                default:
                    g2.getButtonAt(myHint.getX(),myHint.getY()).setStyle("-fx-min-height: 75px; -fx-min-width: 75px; "
                                                                        + "-fx-background-color: #e5ff00;");
                    break;
            }
           return myHint.getMsg();   
    }
    
    
    //checks each hint in list if that space is blank display hint else check if any errors if no error check next hint in list
    public Hint getHint(GridModel g1,GridModel g2,GridModel g3){
        for(Hint h: hints){
            switch (h.getGridNum()) {
                case 0:
                    if(g1.isClearAt(h.getX(), h.getY())){
                        return h;
                    }else{
                        if(!g1.clearErrors()){
                            return new Hint(0,0,-1,"you made a mistake"); 
                        }
                    }    break;
                case 1:
                    if(g3.isClearAt(h.getX(), h.getY())){
                        return h;
                    }else{
                        if(!g3.clearErrors()){
                            return new Hint(0,0,-1,"you made a mistake"); 
                        }
                    }    break;
                default:
                    if(g2.isClearAt(h.getX(), h.getY())){
                        return h;
                    }else{
                        if(!g2.clearErrors()){
                            return new Hint(0,0,-1,"you made a mistake"); 
                        }
                    }   break;
            }
        }
        
        return new Hint(0,0,-1," you won! no more hints"); 
    }
      
}
