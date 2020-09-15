package hk.edu.polyu.comp.comp2021.jungle.model.animal;

import hk.edu.polyu.comp.comp2021.jungle.model.tile;

import static java.lang.Math.abs;

/**
 * the animal lion
 */
public class Lion extends a_piece {
    /**
     * @param team what team is the lion in
     */
    public Lion(int team){
        this.assignTeam(team);
        this.assignRank(7);
    }
    @Override
    public boolean isValid(tile[][] a, tile old_tile, tile new_tile, int team_number){
        if (old_tile.getAnimal().getTeam()!= team_number) return false;
        if (new_tile.getAnimal().getTeam()== team_number) return false;

        if (new_tile.getType()==2) return false;
        if (new_tile.getAnimal().getRank()>this.getRank()) return false;

        int change_x = abs(old_tile.getX() - new_tile.getX());
        int change_y = abs(old_tile.getY() - new_tile.getY());

        if (change_x>1 && change_y>1) return false;

        if (change_x>1) {
            for (int i = old_tile.getX(); i < change_x; i++){
                if (a[i][old_tile.getY()].getAnimal().getRank() == 0) return false;
            }
        }

        else {
            for (int i = old_tile.getY(); i < change_y; i++){
                if (a[i][old_tile.getY()].getAnimal().getRank() == 0) return false;
            }
        }
        return true;
    }

}
