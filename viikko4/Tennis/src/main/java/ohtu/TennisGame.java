package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {

        if (player1Score >= 4 || player2Score >= 4) {
            int difference = player1Score - player2Score;

            if (difference == 0) {
                return "Deuce";
            }

            if (difference == 1 || difference == -1) {
                return getAdvantage(difference);
            }

            return getWinner(difference);

        }

        if (player1Score == player2Score) {
            return scoreToString(player1Score) + "-All";
        }

        return scoreToString(player1Score) + "-" + scoreToString(player2Score);

    }

    public String scoreToString(int score) {
        if (score == 0) {
            return "Love";
        }

        if (score == 1) {
            return "Fifteen";
        }

        if (score == 2) {
            return "Thirty";
        }

        if (score == 3) {
            return "Forty";
        }

        return "";
    }

    public String getWinner(int difference) {
        if (difference >= 2) {
            return "Win for player1";
        }

        return "Win for player2";
    }

    public String getAdvantage(int difference) {
        if (difference == 1) {
            return "Advantage player1";
        }
        return "Advantage player2";
    }

}