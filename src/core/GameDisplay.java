package core;

import Cards.Card;
import Display.Display;
import gameplay.AttackData;
import gameplay.Battle;
import gameplay.Player;
import gameplay.Side;

import java.util.Scanner;

public class GameDisplay
{
    private int _playerWins = 0;
    private int _enemyWins = 0;
    private int _matchNumber = 1;
    private Player _player = new Player();


    public void launchGame(){

        while(this._playerWins < 3 && this._enemyWins < 3){

            if(_matchNumber == 3){
                cardChoice();
            }

            _player.newGame();

            Game currentGame =  new Game(_player);

            while(!currentGame.isGameOver()){


                currentGame.startNewTurn();


                currentGame.getBoard().moveEnemyIntention();
                currentGame.planOpponentNextTurn();



                playerMenu(currentGame);

                currentGame.clearAttackHistory();

                currentGame.executeCombatPhase(Side.PLAYER, Side.ENEMY, currentGame.getEnemy());
                currentGame.executeCombatPhase(Side.ENEMY, Side.PLAYER, currentGame.getPlayer());

            }

            _matchNumber++;

            if(currentGame.playerWin()){
                _playerWins++;
            }else{
                _enemyWins++;
            }

        }

        if(this._playerWins >= 3){
            System.out.println("Félicitations vous avez remporté le jeu !");
        }else{
            System.out.println("Game over, vous avez perdu...");
        }


    }


    public void playerMenu(Game currentGame){
        Scanner scanner = new Scanner(System.in);
        boolean isTurnOver = false;
        String lastAction = "" ;

        while(!isTurnOver){


            clearConsole();

            System.out.println("Partie " + this._matchNumber);
            System.out.println("Score global : Joueur " + this._playerWins + " - " + this._enemyWins + " Adversaire");
            System.out.println("Manche " + currentGame.getTurnCounter());




            Display.DisplayBoard(currentGame);

            displayHistory(currentGame);

            if(!lastAction.isEmpty()){
                System.out.println(lastAction);
            }


            System.out.println("\n Sang : " + currentGame.getPlayer().getBloodStock()
                    + " | Os : " + currentGame.getPlayer().getBoneStock());

            System.out.println("Que voulez vous faire ?");
            System.out.println("1. Piocher une carte");
            System.out.println("2. Jouer une carte");
            System.out.println("3. Sacrifier une créature");
            System.out.println("4. Terminer le tour");
            System.out.print("Votre choix : ");

            int choix = 0;

            if(scanner.hasNextInt()){
                choix = scanner.nextInt();
            }else{
                scanner.next();
            }

            switch (choix){
                case 1:
                    DrawStatus drawStatus = currentGame.draw(currentGame.getPlayer());
                    switch (drawStatus){
                        case SUCCESS:
                            lastAction = "\nVous avez pioché une carte " + currentGame.getPlayer().getHand().getCard(currentGame.getPlayer().getHand().getSize() - 1).get().get_nom() + "!";
                            break;
                        case EMPTY_DECK:
                            lastAction = "\nImpossible de piocher! Votre pioche est vide !";
                            break;
                        case ALREADY_DRAWN:
                            lastAction = "\nImpossible de piocher! Vous avez déjà pioché une carte ce tour";
                            break;
                    }
                    break;
                case 2:
                    System.out.print("\nSélectionnez le numéro de la carte que vous souhaitez jouer : ");
                    if (!scanner.hasNextInt()) {
                        scanner.next();
                        lastAction = "\nErreur : Vous devez entrer un numéro de carte valide !";
                        break;
                    }
                    int handIndex = scanner.nextInt() - 1;

                    System.out.print("\nSélectionnez la case du plateau que souhaitez de jouer : ");
                    String coordonee = scanner.next().toUpperCase();

                    int boardIndex = currentGame.caseTrad(coordonee);

                    PlaceStatus placeStatus = currentGame.placeCard(handIndex,boardIndex);
                    switch (placeStatus){
                        case SUCCESS:
                            lastAction = "\nLa carte " + currentGame.getBoard().getCard(boardIndex, Side.PLAYER).get().get_nom()   + " à bien été placé sur la case B" + (boardIndex + 1);
                            break;
                        case CELL_OCCUPIED:
                            lastAction = "\nImpossible de jouer! La case choisi est déjà occupé par une autre carte !";
                            break;
                        case CARD_NOT_FOUND:
                            lastAction = "\nImpossible de jouer! La carte n'a pas été trouvé";
                            break;
                        case INVALID_INDEX:
                            lastAction = "\nImpossible de jouer! La case n'existe pas ou ne vous appartient pas!";
                            break;
                        case NOT_ENOUGH_BLOOD:
                            lastAction = "\nImpossible de jouer cette carte! Vous n'avez pas assez de sang disponible!";
                            break;
                        case NOT_ENOUGH_BONES:
                            lastAction = "\nImpossible de jouer cette carte! Vous n'avez pas assez d'os disponible!";
                            break;
                    }
                    break;
                case 3:
                    System.out.println("\nSélectionner la case de la créature à sacrifier :");
                    String coord = scanner.next();
                    int indexBoard = currentGame.caseTrad(coord);
                    if(indexBoard == -1){
                        lastAction = "\nLa case n'existe pas ou ne vous appartient pas!";
                    }
                    else{
                        if(!currentGame.sacrifice(indexBoard)){
                            lastAction = "\nSeulement les cartes Animal peuvent être sacrifié!";
                        }else{
                            lastAction = "\nVous avez sacrifié une carte";
                        }


                    }
                    break;
                case 4:
                    isTurnOver = true;
                    break;
                default:
                    lastAction = "\nVeuillez sélectionnez un choix entre une action entre 1 et 4";

            }

        }

    }


    private void clearConsole() {
        try {
            for(int i = 0; i < 50; i++) {
                 System.out.println();
             }

        } catch (Exception e) {
        }
    }


    private void cardChoice(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Vous êtes à la partie 3, vous devez faire un choix entre ces 2 cartes : ");
        Card firstCard = Card.getRandomAnimalCard();
        Card secondCard = Card.getRandomAnimalCard();

        while (secondCard.get_nom().equals(firstCard.get_nom())) {
            secondCard = Card.getRandomAnimalCard();
        }


        System.out.printf("   1. %-12s PV: %d     Att: %d    Gouttes de sang: %d  Os : %d\n",
                firstCard.get_nom(),
                firstCard.get_healthPoint(),
                firstCard.getAttack(),
                firstCard.getBlood(),
                firstCard.getBone()
        );

        System.out.printf("   2. %-12s PV: %d     Att: %d    Gouttes de sang: %d  Os : %d\n",
                secondCard.get_nom(),
                secondCard.get_healthPoint(),
                secondCard.getAttack(),
                secondCard.getBlood(),
                secondCard.getBone()
        );

        int choix = 0;
        while (choix != 1 && choix != 2) {
            System.out.print("Votre choix (tapez 1 ou 2) : ");

            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                if (choix != 1 && choix != 2) {
                    System.out.println("Erreur : Veuillez taper 1 ou 2.");
                }
            } else {
                System.out.println("Erreur : Saisie invalide, ce n'est pas un chiffre.");
                scanner.next();
            }
        }

        if (choix == 1) {
            System.out.println("Vous avez ajouté " + firstCard.get_nom() + " à votre pioche !");
            _player.getInitialDeck().addCard(firstCard);

        } else {
            System.out.println("Vous avez ajouté " + secondCard.get_nom() + " à votre pioche !");
            _player.getInitialDeck().addCard(secondCard);

        }


    }


    private void displayHistory(Game currentGame){

        int historySize = currentGame.sizeAttackHistory();

        if(historySize > 0){
            System.out.println("\n-- RESULTAT DES ATTAQUES --");
            for (int i = 0; i < historySize; i++) {
                AttackData data = currentGame.getAttackHistory(i);

                if(data.getDamageDealt() > 0){
                    System.out.printf("La carte %s a inflige %d degats a %s.%n",
                            data.getAttackerName(),
                            data.getDamageDealt(),
                            data.getTargetName()
                    );

                }

                if (data.isLethal()) {
                    System.out.printf("La carte %s a ete eliminee au cours de cet affrontement.%n", data.getTargetName());
                }

                if (data.getOverkillDamage() > 0) {
                    System.out.printf("Des degats supplementaires de %d points ont ete infliges a la balance.%n", data.getOverkillDamage());
                }
            }
        }
    }
}
