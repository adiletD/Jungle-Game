package hk.edu.polyu.comp.comp2021.jungle.model.animal;

/**
 * the animal wolf
 */
public class Wolf extends a_piece {
    /**
     * @param team what team is the wolf in
     */
    public Wolf(int team){
        this.assignTeam(team);
        this.assignRank(4);
    }
}
