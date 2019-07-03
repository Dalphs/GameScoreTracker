package dk.hawkster.gamescoretracker.Model;

import android.util.Log;

import java.util.Arrays;
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
        System.out.println(gameMode + " " + suit + " " + requiredPoints + " " + numberOfWhips
                + " " + Arrays.toString(numberOfTricks) + " " + Arrays.toString(playersWithDecision));

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
            double score = doubleScores[i] > 0 ? Math.ceil(doubleScores[i]) : Math.floor(doubleScores[i]);
            scores[i] = (int) score;
        }
        System.out.println("Scores calculated: " + Arrays.toString(scores));
    }

    public double[] getStandardScores(){
        double[] doubleScores = new double[4];
        int tricks = numberOfTricks[0];
        boolean hasWon = tricks >= requiredPoints;

        double pointsPerTrick = 0;

        for (int i = 7; i <= requiredPoints; i++) {
            pointsPerTrick += 0.5;
            }

        int multiplier = 1;
        if (gameMode == 3){
            multiplier += multiplier;
        }else if (gameMode == 4){
            for (int i = 0; i < numberOfWhips; i++) {
                multiplier += multiplier;
            }
        }
        if(!hasWon){
            multiplier += multiplier;
        }
        if(suit != null) {
            if (suit == 4) {
                multiplier += multiplier;
            }
        }
        if(tricks == 13){
            multiplier += multiplier;
        }


        int pointAwardingTricks = hasWon ? tricks - requiredPoints + 1 : tricks - requiredPoints;
        double totalPointsPerPlayer = pointAwardingTricks * pointsPerTrick * multiplier;

        if(playersWithDecision.length == 1){
            for (int i = 0; i < doubleScores.length; i++) {
                doubleScores[i] = playersWithDecision[0] == i + 1 ? totalPointsPerPlayer * 3 : -totalPointsPerPlayer;
            }
        }else {
            for (int i = 0; i < doubleScores.length; i++) {
                if(playersWithDecision[0] == i + 1){
                    doubleScores[i] = totalPointsPerPlayer;
                }else if (playersWithDecision[1] == i + 1){
                    doubleScores[i] = totalPointsPerPlayer;
                }else{
                    doubleScores[i] = -totalPointsPerPlayer;
                }
            }
        }
        System.out.println("doubleScores calculated: " + Arrays.toString(doubleScores));
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
