package main.java;

public class Scores implements Comparable{
    private String name;
    private int highscore;


    public Scores( String name , int highscore)
    {
        this.name = name;
        this.highscore = highscore;

    }

    public int getHighscore() {
        return highscore;
    }

    public int compareTo(Object b) {
        int comparescore = ((Scores)b).getHighscore();
        return -(this.highscore -comparescore) ;
    }


    public String getName() {
        return name;
    }

}
