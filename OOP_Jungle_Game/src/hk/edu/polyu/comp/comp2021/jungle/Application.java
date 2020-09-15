package hk.edu.polyu.comp.comp2021.jungle;

import hk.edu.polyu.comp.comp2021.jungle.model.JungleGame;

import java.io.*;
import java.util.Scanner;
import java.util.HashSet;
import java.util.Set;


/**
 * this is the class that prints out the main menu and handles the desired actions of user
 */
public class Application implements Serializable {


    /**
     * the filenames set stores the names of the created files (saved games)
     * it provides uniqueness for each filename, preventing repetitive naming
     */
    public static final Set<String> fileNames = new HashSet<>();


    /**
     * @param str name of the game
     * @return returns true if the name of the game is successfully added to set
     */
    public static boolean appendSet(String str){
        boolean added;
        added = fileNames.add(str);
        return added;
    }


    /**
     * main method that launches the program and prints out the main menu
     * @param args an array of commandline arguments for the application
     * @throws IOException when can not read the file fileLocations.txt
     */
    public static void main(String[] args) throws IOException {


        File file = new File("fileLocations.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st = null;
        boolean addedToSet;
        while (true) {
            try {
                if ((st = br.readLine()) == null) break;

            } catch (IOException e) {
                e.printStackTrace();
            }

            assert st != null;
            if (st.contains("\\")) {
                addedToSet = appendSet(st.substring(st.lastIndexOf('\\') + 1));
            } else {
                addedToSet = appendSet(st);
            }

            if (!addedToSet) {
                System.out.println("Your file with file locations contain files with similar names");
                System.exit(0);
            }
        }

        mainMenu();
    }

    /**
     * @return returns the input received from the user
     */
    private static int inputMainMenu(){
        int input;
        System.out.println("Hey! Choose what you want to do:\n 1. New game " +
                "\n 2. Continue saved game" + "\n 3. Quit game");

        while(true) {
            try {
                Scanner begin = new Scanner(System.in);
                input = begin.nextInt();
            } catch (Exception e) {
                System.out.println("The input is invalid. Please try again. ");
                continue;
            }
            if(input>3 || input<1){
                System.out.println("The input is invalid. Please try again. ");
                continue;
            }
            break;
        }
        return input;
    }

    /**
     * prints out the main menu
     * @throws IOException if problems with opening or reading file occurs
     */
    public static void mainMenu() throws IOException {

        int input = inputMainMenu();

        switch (input) {
            case 1:
                JungleGame.newGame();
                System.out.print("Bye and See you next time!");
                break;
            case 2:
                JungleGame.continueGame();
                System.out.print("Bye and See you next time!");
                break;
            case 3:
                System.out.print("Bye and See you next time!");
                System.exit(0);
                break;


        }

    }

}

