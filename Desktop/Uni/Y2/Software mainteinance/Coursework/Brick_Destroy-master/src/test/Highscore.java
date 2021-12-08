package test;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Highscore {

    private static Wall wall;

    public Highscore() throws IOException {

        //File highscore = new File("Highscore.txt");
        /*try {
            FileWriter myWriter = new FileWriter("Highscore.txt");

            myWriter.write("Files in java might be tricky");
            myWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }*/
    }

    public static void write(){
        try {
            FileWriter myWriter = new FileWriter("Highscore.txt");

            //FileWriter append = new FileWriter(myWriter,true);

//            myWriter.write("Leaderboard");
            // need to write the score into the leaderboard
//            myWriter.write(wall.getFinalHighScore());
            myWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred");
            e.printStackTrace();
        }

    }

    public static void read(){
        try {
            File myObj = new File("Highscore.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
