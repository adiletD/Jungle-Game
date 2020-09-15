package hk.edu.polyu.comp.comp2021.jungle.model;
import hk.edu.polyu.comp.comp2021.jungle.model.animal.*;

import java.io.Serializable;

/**
 * this class is where the position of animals is created and modified
 */
class Board implements Serializable {
    public final tile[][] a = new tile[20][20];

    private void initialize(){
        initialize_tile();
        initialize_pieces();
    }

    /**
     * initialize the board
     */
    public Board(){
        this.initialize();
    }

     private void initialize_tile() {
        empty empty = new empty(9);

        for(int x = 0;x <= 6;x++){
            for(int y = 0;y <= 8;y++){
                //initialize ground
                a[x][y] = new tile(empty,1, x, y);
                //initialize river
                if((y > 2 && y < 6) && (x != 0 && x != 3 && x !=6)){
                    a[x][y].assignType(2);

                }
                //initialize den
                else if(x == 3 &&(y == 0 || y == 8)){
                    a[x][y].assignType(4);
                }
            }
        }

        //initialize trap
        a[2][0].assignType(3);
        a[3][1].assignType(3);
        a[4][0].assignType(3);
        a[2][8].assignType(3);
        a[3][7].assignType(3);
        a[4][8].assignType(3);

    }

    private void initialize_pieces() {
        //team 1
        a[0][0].assignAnimal(new Tiger(1));
        a[6][0].assignAnimal(new Lion(1));
        a[1][1].assignAnimal(new Cat(1));
        a[5][1].assignAnimal(new Dog(1));
        a[0][2].assignAnimal(new Elephant(1));
        a[2][2].assignAnimal(new Wolf(1));
        a[4][2].assignAnimal(new Leopard(1));
        a[6][2].assignAnimal(new Rat(1));

        //team 2
        a[6][8].assignAnimal(new Tiger(2));
        a[0][8].assignAnimal(new Lion(2));
        a[5][7].assignAnimal(new Cat(2));
        a[1][7].assignAnimal(new Dog(2));
        a[6][6].assignAnimal(new Elephant(2));
        a[4][6].assignAnimal( new Wolf(2));
        a[2][6].assignAnimal( new Leopard(2));
        a[0][6].assignAnimal( new Rat(2));

    }

    /**
     * @param old_x the previous x axis
     * @param old_y the previous y axis
     * @param new_x the now x axis
     * @param new_y the now y axis
     * @param team the
     * @return 0: if it invalid, 1: if team1 wins, 2: if team2 wins, 3: if neither of these
     */
    public int move(int old_x, int old_y, int new_x, int new_y, int team){
        empty empty = new empty(0);

        tile old_tile=a[old_x][old_y];
        tile new_tile=a[new_x][new_y];

        if(old_tile.getAnimal().isValid(a,old_tile,new_tile, team)){
            a[new_x][new_y] = new tile(old_tile.getAnimal(),new_tile.getType(), new_x, new_y);
            a[old_x][old_y] = new tile(empty,a[old_x][old_y].getType(), old_x, old_y);

            if (new_tile.getAnimal().getTeam()==1){
                a_piece.pieces_eaten(1);
            }
            if (new_tile.getAnimal().getTeam()==2){
                a_piece.pieces_eaten(2);
            }

            if (winning_condition()==1) return 1;
            if (winning_condition()==2) return 2;
            else return 3;
        }
        else{
            return 0; //illegal move, ask for new instruction
        }
    }

    private int winning_condition(){
        //winning condition for team 2
        if (a[3][8].getAnimal().getRank() == 1) return 1;
        if (a_piece.pieces_left(2)==0) return 1;

        //winning condition for team 1
        if (a[3][0].getAnimal().getRank() == 2) return 2;
        if (a_piece.pieces_left(1) == 0) return 2;

        else return 3;
    }

    /**
     * function to print out the board
     */
    public void printOutTheBoard(){
        for(int y = 8;y != -1; y--){
            System.out.printf("%s |", y+1);
            for(int x = 0;x <= 6; x++){
                if((2 < y && y < 6) && (x != 0) && (x!=3) &&(x!=6)){
                    System.out.printf( "|" + "%s" + "|", a[x][y].getAnimal().getRank());
                }
                else {
                    System.out.printf( " " + "%s" + " ", a[x][y].getAnimal().getRank());
                }

            }
            System.out.println();

        }
        System.out.println("_______________________");
        System.out.println("    A  B  C  D  E  F  G");
    }

}


