package ohtu;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            m_score1 += 1;
        else
            m_score2 += 1;
    }
    
    public String drawToString() {
        String score = scoreToString(m_score1);
        if (score.equals("Deuce")) {
            return score;
        } else {
            return score + "-All";
        }
    }
    
    public String advantageToString() {
        int minusResult = m_score1-m_score2;
        if (minusResult==1) return "Advantage player1";
        else if (minusResult ==-1) return "Advantage player2";
        else if (minusResult>=2) return "Win for player1";
        else return "Win for player2";
    }

    
    public String scoreToString(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }
    
    public String getScore() {
        if (m_score1==m_score2) {
            return drawToString();
        } else if (m_score1>=4 || m_score2>=4) {
            return advantageToString();
        } else {
            return scoreToString(m_score1) + "-" + scoreToString(m_score2);
        }
    }
}