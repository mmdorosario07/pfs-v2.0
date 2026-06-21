public class User {
    private String name;
    private String pswd;
    private int last_ranking;

    private String players[] = {"Admin", "Master"};
 
    public int getRanking() {
        return last_ranking;
    }
    public String getPswd() {
        return pswd;
    }
    public String getName() {
        return name;
    }

    public void setRanking(int ranking, int points, int id) {
        //send to log file
        System.out.println("Player: "+players[id]+"@"+name+" set Ranking to "+ranking+" >> "+points+" pts");
        last_ranking = ranking;
    }

    public void setName(String new_name, int id) {
        //send to log file
        System.out.println("Player "+id+"@"+name+" changed name to "+id+"@"+new_name);
        name = new_name;
    }

    public void setPswd(String new_pswd, int id) {
        //send to log file
        System.out.println("Player "+id+"@"+name+" changed his password");
        pswd = new_pswd;
    }
}