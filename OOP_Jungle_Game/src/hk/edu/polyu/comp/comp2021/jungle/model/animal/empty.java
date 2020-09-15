package hk.edu.polyu.comp.comp2021.jungle.model.animal;

/**
 * when there is nothing in the board (to print out 0 if there is no animal, and to prevent a null pointer exception)
 */
public class empty extends a_piece {
    /**
     * @param team what team is the in (use 9 to not mislead it with team1 or team2)
     */
    public empty(int team){
        this.assignTeam(team);
        this.assignRank(0);
    }
}
