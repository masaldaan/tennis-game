package org.sports;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameTest {

    @Test
    void simulateLoveGame() {
        Game game = new Game();
        Player sid = new Player("sid");
        Player rafa = new Player("rafa");
        game.started(sid, rafa);

        assertEquals("0", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());

        game.pointPlayed(new Point(sid, rafa, "server"));

        assertEquals("15", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());

        game.pointPlayed(new Point(rafa, sid, "receiver"));
        assertEquals("30", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());

        game.pointPlayed(new Point(sid, rafa, "server"));
        assertEquals("40", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());

        game.pointPlayed(new Point(rafa, sid, "receiver"));
        assertEquals("game", game.getPlayer1Score());
        assertEquals("0", game.getPlayer2Score());

        assertTrue(game.hasGameEnded());
    }

    @Test
    void simulatePlayer2Wins() {
        Game game = new Game();
        Player sid = new Player("sid");
        Player federer = new Player("federer");
        game.started(sid, federer);

        game.pointPlayed(new Point(federer, sid, "server"));
        game.pointPlayed(new Point(sid, federer, "receiver"));
        game.pointPlayed(new Point(federer, sid, "server"));
        game.pointPlayed(new Point(sid, federer, "receiver"));

        assertEquals("game", game.getPlayer2Score());
        assertEquals("0", game.getPlayer1Score());

        assertTrue(game.hasGameEnded());
        assertEquals("federer", game.winner().name());
    }

    @Test
    void simulateDeuceGame() {
        Game game = new Game();
        Player sid = new Player("sid");
        Player federer = new Player("federer");
        game.started(sid, federer);
        game.pointPlayed(new Point(federer, sid, "server"));
        game.pointPlayed(new Point(sid, federer, "server"));
        game.pointPlayed(new Point(federer, sid, "server"));
        game.pointPlayed(new Point(sid, federer, "server"));
        game.pointPlayed(new Point(federer, sid, "server"));
        game.pointPlayed(new Point(sid, federer, "server"));

        assertEquals("40", game.getPlayer1Score());
        assertEquals("40", game.getPlayer2Score());
        game.pointPlayed(new Point(federer, sid, "server"));

        assertEquals("adv", game.getPlayer2Score());

        game.pointPlayed(new Point(sid, federer, "server"));

        assertEquals("40", game.getPlayer1Score());
        assertEquals("40", game.getPlayer2Score());
        game.pointPlayed(new Point(federer, sid, "server"));
        game.pointPlayed(new Point(sid, federer, "receiver"));

        assertEquals("40", game.getPlayer1Score());
        assertEquals("game", game.getPlayer2Score());

        game.hasGameEnded();
        assertEquals("federer", game.winner().name());
    }

}
