package Display;


import Card.Card;
import core.Game;

import java.util.Optional;

public class Display {
    public static void start(){
        System.out.println("/////////////////////////////////////////");
        System.out.println("///////////////INSCRYPTION///////////////");
        System.out.println("/////////////////////////////////////////");
    }



    public void showBoard(Game engine){


        System.out.println("Partie 1");
        System.out.println("Manche 1");

    }


    public void showLine(Optional<Card>[] line, int nbLine){
        for(int i = 0; i < 7;i++){

            constructLineCard(line,i,nbLine);
        }
    }


    public void constructLineCard(Optional<Card>[] line, int index, int nbLine) {

        if (index < 0 || index > 6) {
            System.out.print("Index out of range : " + index);
            return;
        }


        for (int i = 0; i < 4; i++) {
            Optional<Card> OptionalCard = line[i];

            if (OptionalCard.isPresent()) {

                Card card = OptionalCard.get();
                switch (index) {
                    case 0: System.out.print("*-----------*   "); break;
                    case 1: System.out.printf("| %-10s|   ", card.get_nom()); break;
                    case 2: System.out.print("|-----------|   "); break;
                    case 3: System.out.printf("| %-10s|   ", card.displayHp()); break;
                    case 4: System.out.printf("| %-10s|   ", card.displayAttack()); break;
                    case 5: System.out.printf("| %-10s|   ", card.displaySigil()); break;
                    case 6: System.out.print("*-----------*   "); break;
                }
            } else {
                switch (index) {
                    case 0:
                        System.out.print("*************   ");
                        break;
                    case 3:
                        if (nbLine == 1) {
                            System.out.printf("*     A%d    *   ", i + 1);
                        } else if (nbLine == 2) {
                            System.out.printf("*     B%d    *   \"", i + 1);
                        } else {
                            System.out.print("*           *   ");
                        }
                        break;
                    default:
                        System.out.print("*           *   ");
                        break;
                }
            }
        }
    }




}
