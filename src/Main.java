import core.Game;
import Display.Display;

public class Main {
    public static void main(String[] args) {

        // 1. Initialisation
        Game engine = new Game();
        Display ecran = new Display();

        System.out.println("========== AVANT LA PIOCHE ==========");
        ecran.DisplayBoard(engine);

        // --- TEST DE LA PIOCHE ---
        System.out.println("\n>>> Action : Le joueur tente de piocher... <<<");

        try {
            // On appelle ta méthode via le Game
            engine.draw(engine.getPlayer());
            System.out.println("Succès ! La carte a été ajoutée à la main.");

            // TEST DE SÉCURITÉ : On essaie de tricher et de repiocher tout de suite !
            System.out.println("\n>>> Action : Le joueur tente de tricher et repiocher... <<<");
            engine.draw(engine.getPlayer());

        } catch (IllegalStateException e) {
            // Si le jeu bloque la deuxième pioche, on atterrit ici :
            System.out.println("Bloqué par les règles : " + e.getMessage());
        }

        System.out.println("\n========== APRÈS LA PIOCHE ==========");
        ecran.DisplayBoard(engine);
    }
}