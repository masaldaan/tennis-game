package org.sports;

import java.util.List;
import java.util.Map;

public class Game {

    private Player player1;
    private Player player2;
    private Player winner;
    private final Map<String, String> points = Map.of(
            "0", "15",
            "15", "30",
            "30", "40",
            "40", "game"
    );

    private final Map<String, String> deucePoints =
            Map.of(
                    "40-40-server", "adv-40",
                    "40-40-receiver", "adv-40",
                    "40-adv-server", "40-40",
                    "adv-40-server", "game-40",
                    "40-adv-receiver", "40-game",
                    "adv-40-receiver", "40-40"
            );

    public String getPlayer1Score() {
        return player1.score();
    }

    public String getPlayer2Score() {
        return player2.score();
    }

    public void started(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void playGame(Player player1, Player player2, List<Point> pointsPlayed) {
        started(player1, player2);
        for (Point point: pointsPlayed) {
            pointPlayed(point);
            if (hasGameEnded()) {
                System.out.println(winner.name() + " has won!!!");
                return;
            }
        }
    }

    public boolean hasGameEnded() {
        if (player1.score().equalsIgnoreCase("game")
        ) {
            this.winner = player1;
            return true;
        } else if (player2.score().equalsIgnoreCase("game")
        ) {
            this.winner = player2;
            return true;
        }
        return false;
    }

    public void pointPlayed(Point point) {
        // Deuce points
        if (isDeuceOrAdv(point.server(), point.receiver())) {
            String key = point.server().score() + "-" + point.receiver().score() + "-" + point.winner();
            String nextPoint = deucePoints.get(key);
            String[] split = nextPoint.split("-");
            point.server().updateScore(split[0]);
            point.receiver().updateScore(split[1]);
            return;
        }
        // Regular points
        if (point.winner().equals("server")) {
            point.server().updateScore(calculateNextPoint(point.server()));
        } else if (point.winner().equals("receiver")) {
            point.receiver().updateScore(calculateNextPoint(point.receiver()));
        }
    }

    private static boolean isDeuceOrAdv(Player server, Player receiver) {
        return (server.score().equalsIgnoreCase("40") || server.score().equalsIgnoreCase("adv"))
                && (receiver.score().equalsIgnoreCase("adv") || receiver.score().equalsIgnoreCase("40"));
    }

    private String calculateNextPoint(Player server) {
        return points.get(server.score());
    }

    public Player winner() {
        return winner;
    }
}
