
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private int goals;
    private int assists;
    private String nationality;

    public void setName(String name) {
        this.name = name;
    }
    public void setTeam(String team) { this.team = team; }
    public void setGoals(int goals) { this.goals = goals; }
    public void setAssists(int assists) { this.assists = assists; }
    public void setNationality(String nationality) { this.nationality = nationality; }

    public String getName() {
        return name;
    }
    public String getTeam() { return team; }
    public int getGoals() { return goals; }
    public int getAssists() { return assists; }
    public String getNationality() { return nationality; }

    @Override
    public String toString() {
        return name + " team " + team + " " + goals + " + " + assists + " = " + (goals + assists);
    }

    @Override
    public int compareTo(Player p){
        int points = this.goals + this.assists;
        int compPoints = p.goals + p.assists;
        return compPoints - points;


    }
      
}
