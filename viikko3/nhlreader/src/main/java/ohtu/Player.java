
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private int goals;
    private int assists;
    private String team;
    private String nationality;
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public int compareTo(Player comparedPlayer) {
        int comparePoints =((Player)comparedPlayer).getPoints();
        return comparePoints - this.getPoints();
    }

    @Override
    public String toString() {
        return name + "\t" + team + "\t" + goals + " + " + assists + " = " + points;
    }

        

      
}
