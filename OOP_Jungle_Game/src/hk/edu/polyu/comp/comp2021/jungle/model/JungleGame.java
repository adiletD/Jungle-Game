package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.Application;
import java.util.Scanner;
import java.io.*;


/**
 * class for creating, loading, saving games and for handling user inputs
 */
public final class JungleGame implements Serializable{
    private String player1;
    private String player2;
    private final Board board;
    private int teamnum = 1;


    JungleGame(){
        board = new Board();
    }

    public static void putName(JungleGame game, String player1, String player2){
        game.player1=player1;
        game.player2=player2;
    }

    private void changeturn(){
        if(teamnum ==1){
            teamnum = 2;
        }
        else if(teamnum == 2)
            teamnum = 1;
    }

    private void startGame() throws IOException {

        //do move, save, open
        while(true){

            //print out the board
            System.out.println();
            System.out.println("     player 2: " + player2);
            this.board.printOutTheBoard();
            System.out.println("     player 1: " + player1 );
            System.out.println();

            if(teamnum == 1){
                System.out.println("it is " +player1 +"\'s turn");
            }
            else if(teamnum == 2){
                System.out.println("it is " +player2 +"\'s turn");
            }

            while(true) {
                try {
                    System.out.println("Make a move, save or open saved game. Sample moves:  ");
                    System.out.println("\"move\" + initial position + final position (ex: move C3 C7) ");
                    System.out.println("\"save\" + location to save + filename(with no extension) (ex: save G:\\\\address\\filename) ");
                    System.out.println("\"open\" + location to upload from + filename(with no extension) (ex: open G:\\\\address\\filename) ");
                    System.out.println("Or just type \"open\" to open file from list of saved games");

                    System.out.println("Type \"exit\" to go back to main menu");

                    String str = new Scanner(System.in).nextLine();

                    String[] arrOfStr = str.split(" ");
                    int strLen = str.length();

                    final int ascii_to_int = 65;
                    switch (arrOfStr[0]) {
                        case "move": //need error handling if input is larger than something then cannot
                            int a = arrOfStr[1].charAt(0) - ascii_to_int;
                            int b = Character.getNumericValue(arrOfStr[1].charAt(1));
                            int c = arrOfStr[2].charAt(0) - ascii_to_int;
                            int d = Character.getNumericValue(arrOfStr[2].charAt(1));

                            int valid;

                            if (a > 7 || c > 7 || b > 10 || d > 10 || b == 0 || d == 0) {
                                valid = 0;
                            } else {
                                b--;
                                d--;
                                valid = board.move(a, b, c, d, teamnum);
                            }

                            if (valid == 0) {
                                System.out.println("Is invalid Please input again");
                                startGame();
                                return;

                            } else if (valid == 1) {
                                win(1);
                                return;

                            } else if (valid == 2) {
                                win(2);
                                return;

                            } else {
                                changeturn();
                                startGame();
                                return;
                            }

                        case "save":
                            boolean saved = this.saveGame(str.substring(5, strLen));
                            if (!saved) {
                                continue;
                            }
                            break;
                        case "open":
                            openGame(str);
                            break;
                        case "exit":
                            Application.mainMenu();
                        default:
                            System.out.println("Your input is invalid. Something went wrong. Please try again. \n");
                            continue;
                    }
                }catch(Exception e){
                    System.out.println("Your input is invalid. Something went wrong. Please try again. \n");
                    continue;
                }
                break;
            }
        }
    }

    //method for saving the game
    public boolean saveGame(String str) throws IOException {
        boolean saved;

        String fileName = str.substring(str.lastIndexOf('\\')+1);
        //saving the name of the file
        saved = Application.appendSet(fileName);
        if(!saved){
            System.out.println("Not valid name. Such game already exists");
            return false;
        }

        //writing the location of the file into the txt = appending the txt file

        BufferedWriter output;
        output = new BufferedWriter(new FileWriter("fileLocations.txt",true));
        output.append(str);
        output.newLine();
        output.close();

        //saving the state of board
        try {
            FileOutputStream fileStream = new FileOutputStream(str +".ser");
            ObjectOutputStream os = new ObjectOutputStream(fileStream);
            os.writeObject(this);
            os.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }

        System.out.println("Saved successfully");
        return true;
    }


    private void openGame(String str) throws IOException {
        Scanner integer = new Scanner(System.in);
        System.out.println("Do you want to save your current game? (type 1 if yes, any other number if no) ");
        int input = integer.nextInt();
        if(input==1){
            System.out.println("How do you want to save your game?(type the location ) ");
            String strName = new Scanner(System.in).nextLine();
            this.saveGame(strName);
        }

        if(str.trim().equals("open"))
            continueGame();
        else{
            continueGame(str.substring(5));
        }

    }


    /**
     * @throws IOException
     * creates a new game with names of players coming as input
     */
    public static void newGame() throws IOException {

        String player1,player2;
        while(true) {
            Scanner p1 = new Scanner(System.in);
            System.out.println("Enter the name of the first player: ");
            player1 = p1.nextLine();

            Scanner p2 = new Scanner(System.in);
            System.out.println("Enter the name of the second player: ");
            player2 = p2.nextLine();

            if (player1.trim().length() == 0 || player2.trim().length() == 0) {
                System.out.println("One or both of the names are invalid. Try again.");
                continue;
            }
            break;
        }

            JungleGame game = new JungleGame();
            putName(game, player1, player2);
            game.startGame();

    }


    /**
     * @throws FileNotFoundException
     * enables the user to load games
     */
    //method for loading saved game session
    public static void continueGame() throws FileNotFoundException {
        System.out.println("Your saved games: ");

        //prints out names of all saved games
        for (String s : Application.fileNames) {
            System.out.println(s);
        }

        //input and check for presence of the game file
        Scanner prompt = new Scanner(System.in);
        System.out.println("Which game you would like to open? ");
        String file;
        while(true) {
            file = prompt.nextLine();
            if(!Application.fileNames.contains(file)){
                System.out.println("Such game doesn't exist. Try again...");
            }else{
                break;
            }
        }

        Scanner scanner = new Scanner(new File("fileLocations.txt"));
        String line = null;
        while(scanner.hasNextLine()){
            line = scanner.nextLine();
            if(line.contains(file)){
                break;
            }
        }

        //pass the address to open the file

        try {
            FileInputStream fileStream = new FileInputStream(line + ".ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            Object game = os.readObject();
            JungleGame loadedGame2 = (JungleGame) game;
            loadedGame2.startGame();
            os.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void continueGame(String str){

        try{
            FileInputStream fileStream = new FileInputStream(str + ".ser");
            ObjectInputStream os = new ObjectInputStream(fileStream);
            Object game = os.readObject();
            JungleGame loadedGame = (JungleGame) game;
            loadedGame.startGame();
            os.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }


    private void win(int x){
        System.out.println("Congratulations! Team" + x + "won!");

    }
}


