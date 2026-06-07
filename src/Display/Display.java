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



    public static void DisplayBoard(Game engine){

        DisplayLine(engine.getBoard().getEnemyIntentionsLine(), 0);

        System.out.println("      ||              ||              ||              ||");
        System.out.println("      \\/              \\/              \\/              \\/");


        DisplayLine(engine.getBoard().getEnemyLine(), 1);
        System.out.println();

        DisplayLine(engine.getBoard().getPlayerLine(), 2);
        System.out.println("                           Score: " + engine.getScore().getCurrentScore());




        System.out.println("                                                                           Pioche : ");
        System.out.println("                                                                           *-----------*");
        System.out.println("Your hand :                                                                |           |");

        int nb = 5;
        if(engine.getPlayer().getHand().getSize() > nb){
            nb = engine.getPlayer().getHand().getSize();
        }

        for(int i = 0 ;i < nb; i++){

            Optional<Card> optCard = engine.getPlayer().getHand().getCard(i);
            if(optCard.isPresent()){
                Card card = optCard.get();

                System.out.printf("   %d. %-12s PV: %d     Att: %d    Gouttes de sang: %d  Os : %d",
                        i + 1,
                        card.get_nom(),
                        card.get_healthPoint(),
                        card.getAttack(),
                        card.getBlood(),
                        card.getBone()
                );

                System.out.print("          ");



            }else{
                System.out.print("                                                                           ");
            }

            constructDeck(i,engine);






        }

        /*System.out.println("Pioche : ");
        System.out.println("*-----------*");
        System.out.println("|           |");
        System.out.println("|           |");
        System.out.printf("|     %-6d|%n", engine.getPlayer().getDeck().getSizeDeck());
        System.out.println("|   cartes  |");
        System.out.println("|           |");
        System.out.println("*-----------*");*/



    }

    public static void constructDeck(int index, Game engine){
        switch(index){
            case 0:
                System.out.println("|           |");
                break;
            case 1:
                System.out.printf("|     %-6d|%n", engine.getPlayer().getDeck().getSizeDeck());
                break;
            case 2:
                System.out.println("|   cartes  |");
                break;
            case 3:
                System.out.println("|           |");
                break;
            case 4:
                System.out.println("*-----------*");
                break;
            default:
                break;

        }

    }


    public static void DisplayLine(Optional<Card>[] line, int nbLine){
        for(int i = 0; i < 7;i++){

            constructLineCard(line,i,nbLine);
            System.out.println("");
        }

    }


    public static void constructLineCard(Optional<Card>[] line, int index, int nbLine) {

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
                            System.out.printf("*     B%d    *   ", i + 1);
                        } else {
                            System.out.print("*           *   ");
                        }
                        break;
                    case 6:
                        System.out.print("*************   ");
                        break;
                    default:
                        System.out.print("*           *   ");
                        break;
                }
            }
        }
    }






}
