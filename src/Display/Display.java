package Display;


import Cards.Card;
import core.Game;
import core.PlaceStatus;
import gameplay.Deck;

import java.util.ArrayList;
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

        DisplayHand(engine);



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
                System.out.println("");
                break;

        }

    }


    public static void DisplayLine(Optional<Card>[] line, int nbLine){


        int maxPowers = 1;
        for(int i = 0; i < 4; i++){
            if(line[i].isPresent()){
                int powersCount = line[i].get().getSizePower();
                if(powersCount > maxPowers){
                    maxPowers = powersCount;
                }
            }
        }

        int totalLine = 7 + maxPowers;
        int lastIndex = totalLine - 1;


        for(int i = 0; i <= lastIndex;i++){
            constructLineCard(line,i,nbLine, lastIndex);
            System.out.println("");
        }

    }


    public static void constructLineCard(Optional<Card>[] line, int index, int nbLine, int lastIndex) {

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
                    default:
                        if(index == lastIndex){
                            System.out.print("*-----------*   ");
                        }else{
                            int powerIndex = index - 6;
                            System.out.printf("| %-10s|   ", card.displayPower(powerIndex));
                        }
                    break;
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
                    default:
                        if(index == lastIndex){
                            System.out.print("*************   ");
                        }else{
                            System.out.print("*           *   ");
                        }
                        break;

                }
            }
        }
    }


    public static void DisplayHand(Game engine){

        System.out.println("                                                                                                         Pioche : ");
        System.out.println("                                                                                                         *-----------*");
        System.out.println("Ta main :                                                                                              |           |");

        int nb = 5;
        if(engine.getPlayer().getHand().getSize() > nb){
            nb = engine.getPlayer().getHand().getSize();
        }

        for(int i = 0 ;i < nb; i++){

            Optional<Card> optCard = engine.getPlayer().getHand().getCard(i);
            if(optCard.isPresent()){
                Card card = optCard.get();

                displayCard(card, i);

                System.out.print("          ");



            }else{
                System.out.print("                                                                                                         ");
            }

            constructDeck(i,engine);

        }
    }


   /* public static void DisplayDeck(Deck deck){


        for(int i = 0; i < deck.getSizeDeck();i++){
            System.out.printf("Votre pioche : \n");
            displayCard(lst.get(i),i);
            System.out.println("");

        }
    }

    */


    public static void displayCard(Card card, int i){

        String powerStr  = "Aucun pouvoir";

        if(card.getSizePower() > 0)
        {
            if(card.getPowers(0).isPresent()){
                powerStr = card.getPowers(0).get().getName();
            }
        }


        System.out.printf("   %d. %-12s PV: %d     Att: %d    Gouttes de sang: %d  Os : %d    Pouvoir : %-16s",
                i + 1,
                card.get_nom(),
                card.get_healthPoint(),
                card.getAttack(),
                card.getBlood(),
                card.getBone(),
                powerStr
        );

    }







}
