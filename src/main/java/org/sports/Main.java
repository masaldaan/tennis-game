package org.sports;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        Game game = new Game();

        Player sid = new Player("sid");
        Player federer = new Player("federer");
        game.playGame(
                sid,
                federer,
                List.of(
                        new Point(federer, sid, "server"),
                        new Point(sid, federer, "server"),
                        new Point(federer, sid, "server"),
                        new Point(sid, federer, "server"),
                        new Point(federer, sid, "server"),
                        new Point(sid, federer, "server"),

                        new Point(federer, sid, "server"),

                        new Point(sid, federer, "server"),

                        new Point(federer, sid, "server"),
                        new Point(sid, federer, "receiver"))
        );
        System.out.println(game.winner().name() + " has won!!!");
    }
}
