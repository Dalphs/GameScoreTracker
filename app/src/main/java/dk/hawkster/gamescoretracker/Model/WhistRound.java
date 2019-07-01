package dk.hawkster.gamescoretracker.Model;

import java.util.List;

public class WhistRound {

    Integer gameMode;
    Integer suit;
    Integer requiredPoints;
    Integer numberOfWhips;
    int[] numberOfTricks;
    int[] playersWithDecision;
    int[] scores;


    public WhistRound(Integer gameMode, Integer suit, Integer requiredPoints, Integer numberOfWhips, int[] numberOfTricks, int[] playersWithDecision) {
        this.gameMode = gameMode;
        this.suit = suit;
        this.requiredPoints = requiredPoints;
        this.numberOfWhips = numberOfWhips;
        this.numberOfTricks = numberOfTricks;
        this.playersWithDecision = playersWithDecision;
        scores = new int[4];

    }

    public int[] getScores(){
        return scores;
    }

    public void calculateScores(){
        double[] doubleScores;

        if(gameMode < 5){
            doubleScores = getStandardScores();
        } else{
           doubleScores = getSunScores();
        }

        for (int i = 0; i < doubleScores.length; i++) {
            scores[i] = (int) Math.ceil(doubleScores[i]);
        }
    }

    public double[] getStandardScores(){
        double[] doubleScores = new double[4];

        double totalPoints = 0;
        for (int i = 7; i <= numberOfTricks[0]; i++) {
            totalPoints += 0.5;
        }
        int multiplier = 1;
        if (gameMode == 3){
            multiplier += multiplier;
        }else if (gameMode == 4){
            for (int i = 0; i < numberOfWhips; i++) {
                multiplier += multiplier;
            }
        }
        if (suit == 4){
            multiplier += multiplier;;
        }
        if(numberOfTricks[0] == 13){
            multiplier += multiplier;
        }
        totalPoints *= multiplier;
        if(playersWithDecision.length == 1){
            for (int i = 0; i < doubleScores.length; i++) {
                doubleScores[i] = playersWithDecision[0] == i + 1 ? totalPoints * 3 : -totalPoints;
            }
        }else {
            for (int i = 0; i < doubleScores.length; i++) {
                if(playersWithDecision[0] == i + 1){
                    doubleScores[i] = totalPoints;
                }else if (playersWithDecision[1] == i + 1){
                    doubleScores[i] = totalPoints;
                }else{
                    doubleScores[i] = -totalPoints;
                }
            }
        }
        return doubleScores;
    }

    public double[] getSunScores(){
        double[] doubleScores = new double[]{0, 0, 0, 0};
        int pointsPerTrick;
        if(gameMode == 5){
            pointsPerTrick = 2;
        }else if(gameMode == 6 || gameMode == 7){
            pointsPerTrick = 4;
        }else{
            pointsPerTrick = 6;
        }
        for (int i = 0; i < playersWithDecision.length; i++) {
            int player = playersWithDecision[i];
            boolean haswon;
            if(gameMode == 5 || gameMode == 6){
                haswon = numberOfTricks[i] <= 1;
            }else{
                haswon = numberOfTricks[i] == 0;
            }
            System.out.println("Player" + player + " has won: " + haswon + "\nPointsPerTrick = " + pointsPerTrick);
            for (int j = 0; j < doubleScores.length; j++) {
                if(player == j + 1){
                    doubleScores[j] += haswon ? pointsPerTrick * 3 : -pointsPerTrick * 3;
                }else{
                    doubleScores[j] += haswon ? -pointsPerTrick : pointsPerTrick;
                }
            }
        }
        return doubleScores;

    }
}
