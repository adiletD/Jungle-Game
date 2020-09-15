package hk.edu.polyu.comp.comp2021.jungle.model.animal;

/**
 * the animal leopard
 */
public class Leopard extends a_piece {
    /**
     * @param team what team is the leopard in
     */

    public Leopard(int team){
        this.assignTeam(team);
        this.assignRank(5);
    }
}
