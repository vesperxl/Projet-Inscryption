package core;

import Cards.Animals.Coyote;
import Cards.Animals.Squirrel;
import Cards.Animals.Wolf;
import gameplay.Player;
import gameplay.Side;
import org.junit.Test;
import static org.junit.Assert.*;

public class GameTest {

    @Test
    public void testDraw_Succes() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        DrawStatus statut = engine.draw(testPlayer);

        assertEquals(DrawStatus.SUCCESS, statut);
    }

    @Test
    public void testDraw_AlreadyDrawn() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        engine.draw(testPlayer);
        DrawStatus statut = engine.draw(testPlayer);

        assertEquals(DrawStatus.ALREADY_DRAWN, statut);
    }

    @Test
    public void testDraw_EmptyDeck() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        while(!testPlayer.getDeck().isEmpty()) {
            testPlayer.getDeck().draw();
        }

        DrawStatus statut = engine.draw(testPlayer);

        assertEquals(DrawStatus.EMPTY_DECK, statut);
    }

    @Test
    public void testPlaceCard_InvalidIndex() {
        Game engine = new Game();

        PlaceStatus statut = engine.placeCard(0, 5);

        assertEquals(PlaceStatus.INVALID_INDEX, statut);
    }

    @Test
    public void testPlaceCard_CardNotFound() {
        Game engine = new Game();

        PlaceStatus statut = engine.placeCard(99, 0);

        assertEquals(PlaceStatus.CARD_NOT_FOUND, statut);
    }

    @Test
    public void testPlaceCard_NotEnoughBlood() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        engine.getBoard().removeCard(0, Side.PLAYER);

        testPlayer.getHand().addCard(new Wolf());

        PlaceStatus statut = engine.placeCard(4, 0);

        assertEquals(PlaceStatus.NOT_ENOUGH_BLOOD, statut);
    }

    @Test
    public void testPlaceCard_NotEnoughBones() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        engine.getBoard().removeCard(0, Side.PLAYER);
        testPlayer.getHand().addCard(new Coyote());

        PlaceStatus statut = engine.placeCard(4, 0);

        assertEquals(PlaceStatus.NOT_ENOUGH_BONES, statut);
    }

    @Test
    public void testPlaceCard_Succes() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        engine.getBoard().removeCard(0, Side.PLAYER);

        testPlayer.getHand().addCard(new Squirrel());

        PlaceStatus statut = engine.placeCard(4, 0);

        assertEquals(PlaceStatus.SUCCESS, statut);

        assertTrue(testPlayer.getHand().getCard(4).isEmpty());
    }

    @Test
    public void testPlaceCard_SuccessWithBlood() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        engine.getBoard().removeCard(0, Side.PLAYER);
        engine.getBoard().removeCard(1, Side.PLAYER);

        testPlayer.getHand().addCard(new Squirrel());
        testPlayer.getHand().addCard(new Squirrel());
        testPlayer.getHand().addCard(new Wolf());

        engine.placeCard(4, 0);
        engine.placeCard(4, 1);

        engine.sacrifice(0);
        engine.sacrifice(1);

        assertEquals(2, testPlayer.getBloodStock());

        PlaceStatus statut = engine.placeCard(4, 0);

        assertEquals(PlaceStatus.SUCCESS, statut);
        assertEquals(0, testPlayer.getBloodStock());
    }

    @Test
    public void testPlaceCard_SuccesWithBones() {
        Game engine = new Game();
        Player testPlayer = engine.getPlayer();

        engine.getBoard().removeCard(0, Side.PLAYER);

        testPlayer.getHand().addCard(new Coyote());

        for (int i = 0; i < 4; i++) {
            testPlayer.getHand().addCard(new Squirrel());
            engine.placeCard(5, 0);

            engine.sacrifice(0);
        }

        assertEquals(4, testPlayer.getBoneStock());

        PlaceStatus statut = engine.placeCard(4, 0);

        assertEquals(PlaceStatus.SUCCESS, statut);
        assertEquals(0, testPlayer.getBoneStock());
    }
}