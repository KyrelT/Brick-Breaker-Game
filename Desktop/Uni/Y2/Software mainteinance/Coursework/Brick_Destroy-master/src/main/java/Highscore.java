package main.java;

import main.java.GameBoard;
import main.java.Wall;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Highscore {

    private static Wall wall;
    private static int showhighscore;
    private static int rank = 1;
    private static String row;
    private static String name;
    public static ArrayList<Scores> getaList(){return aList;}
    private static ArrayList<Scores> aList = new ArrayList<Scores>();


    /*public Highscore() throws IOException {

        //File highscore = new File("Highscore.txt");
        try {
            FileWriter myWriter = new FileWriter("Highscore.txt");

            myWriter.write("Files in java might be tricky");
            myWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }
    }
*/

    public static void write() throws IOException{

            showhighscore = wall.getFinalHighScore();

            File writer = new File("src/main/resources/Highscore.txt");

            FileWriter append = new FileWriter(writer,true);

            BufferedWriter buff = new BufferedWriter(append);

            for (int i =0;i <10;i++){
                if(append!=null){
                    append.append(GameBoard.getname());
                    append.append(',');
                    append.append(String.valueOf(showhighscore));
                    append.append(',');
                    append.append(String.valueOf(rank));
                    append.append('\n');

                    append.close();

                    System.out.println("success");
                }
                else{
                    rank=i;
                }
            }


    }


    public static void read() throws IOException {
        File reader = new File("src/main/resources/Highscore.txt");
        BufferedReader textread;

        textread = new BufferedReader(new FileReader(reader));

        for (int i =0;i<9;i++){
            row = textread.readLine();
            System.out.println(row);

            if(row!=null){
                String[] data = row.split(",");

                name = data[0];

                showhighscore = Integer.parseInt(data[1]);

                aList.add(new Scores(name,showhighscore));
            }
        }
        Collections.sort(aList);

    FileWriter writer = new FileWriter(reader);

    for(Scores score: aList){
        writer.write(score.getName() + ',' + score.getHighscore()+ ',' + rank + '\n');
        rank += 1;
    }
    writer.close();
    }

}
