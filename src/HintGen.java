
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
        hints.add(new Hint(0,1,0,"diamond ring and 325$ are explicitly true. Mark the highlighted cell as TRUE\n"));
        hints.add(new Hint(0,1,2,"addison beach and 325$ are explicitly true. Mark the highlighted cell as TRUE\n"));
        hints.add(new Hint(1,3,1,"we know the diamond ring was found at addison, so the earing must have been "
                + "found at heffen lane. Mark the highlighted cell as true.\n"));  
        
        hints.add(new Hint(1,0,0,"the peice at heffen lane sold for less than the 325 diamond ring " 
                + "beacuse we know the earing was found at heffenlane and the only thing less than 325 is 250 "
                + "the earing must have sold for 250. Mark the highlighted cell as TRUE \n"));
        
        hints.add(new Hint(3,3,0,"the wristwatch sold for less than the object found at burr woods "
                + "so the wrist watch can not be the most expensive. Mark the highlighted cell as FALSE \n"));
        
        hints.add(new Hint(2,3,0,"by process of eliminition we know the "
                + "gold chain cost 475. Mark the highlighted cell as TRUE \n"));
        
        hints.add(new Hint(3,2,0,"by process of eliminition we know the "
                + "wristwatch cost 400. Mark the highlighted cell as TRUE \n"));
        
        hints.add(new Hint(3,2,0,"by process of eliminition we know the "
                + "wristwatch cost 400. Mark the highlighted cell as TRUE \n"));
        
         hints.add(new Hint(2,1,1,"the wristwatch sold for 75 less than the object found at "
                 + "burr woods. the gold chain cost 75 more than the wirstwatch so it must have been found "
                 + "at burr woods. Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(0,0,1,"we know the diamond sold for 325 and the "
                 + "item that sold for 325 was found at addison beach. Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(3,2,1,"by process of eliminition we know the "
                + "wristwatch was found at front beach . Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(3,0,2,"we know the earing cost 250 and was found at heffen lane "
                 + "  Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(2,2,2,"we know the wristwatch cost 400 and was found at front beach"
                 + "  Mark the highlighted cell as TRUE \n"));
         
         hints.add(new Hint(1,3,2,"by process of eliminition we know the "
                + "object that cost 475  was found at burr woods . Mark the highlighted cell as TRUE \n"));          
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
