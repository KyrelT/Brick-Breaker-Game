package main.java;

public class Scores implements Comparable{
    private String name;
    private int highscore;


    /**
     * @param name user's name
     * @param highscore user's highscore
     */
    public Scores( String name , int highscore)
    {
        this.name = name;
        this.highscore = highscore;

    }

    /**
     * @return get the final high score
     */
    public int getHighscore() {
        return highscore;
    }

    /**
     * @return a sorted list
     */
    public int compareTo(Object b) {
        int comparescore = ((Scores)b).getHighscore();
        return -(this.highscore -comparescore) ;
    }


    /**
     * @return get user's name
     */
    public String getName() {
        return name;
    }

}
