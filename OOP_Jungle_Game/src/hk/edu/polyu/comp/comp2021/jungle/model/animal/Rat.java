package hk.edu.polyu.comp.comp2021.jungle.model.animal;
import hk.edu.polyu.comp.comp2021.jungle.model.tile;

import static java.lang.Math.abs;

/**
 * the animal rat
 */
public class Rat extends a_piece {
    /**
     * @param team what team is the rat in
     */

    public Rat(int team){
        this.assignTeam(team);
        this.assignRank(1);
    }

    @Override
    public boolean isValid(tile[][] a, tile old_tile, tile new_tile, int team_number){
        if (old_tile.getAnimal().getTeam()!= team_number) return false;
        if (new_tile.getAnimal().getTeam()== team_number) return false;

        if (abs(old_tile.getX()- new_tile.getX()) + abs(old_tile.getY()- new_tile.getY())>1) return false;

        //if also it is an elephant then can
        if (new_tile.getAnimal().getRank()==8) return true;
        return new_tile.getAnimal().getRank() <= this.getRank();
    }


}
