package hk.edu.polyu.comp.comp2021.jungle.model;

import hk.edu.polyu.comp.comp2021.jungle.model.animal.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;


public class JungleGameTest {
    @org.junit.Before
    public void setUp() throws Exception {
    }
/*
    @Test
    public void testJungleGameConstructor() throws IOException {
        JungleGame game = new JungleGame();
        Board board=new Board();
        //rat
//        assert equals(board.a[][], );
        //winning condition
        board.a[3][7].assignAnimal(new Rat(1));
        int answer= board.move(3, 7,3,8,1);
        assert answer == 1;
        //winning condition
        for (int i=0;i<8;i++) {
        a_piece.pieces_eaten(0);
        }
        }
        int answer =

    }
*/
    @Test
    public void testSave() throws IOException {

    JungleGame game1 = new JungleGame();
    game1.saveGame("D:\\\\ourgame");

    JungleGame game2 = new JungleGame();
    boolean check = game2.saveGame("D:\\\\ourgame");

    Assert.assertEquals(check, false);


    }

}