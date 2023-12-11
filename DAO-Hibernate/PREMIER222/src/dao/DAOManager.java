package dao;
import java.util.*;

import Model.Match;
import Model.Team;

public interface DAOManager extends AutoCloseable {
    
	boolean AddTeam(Team oneTeam);
    void ImportTeams(String fileTeams);
    Team GetTeam(String teamAbbreviation);
    String GetTeamAbbreviation(String teamName);
    boolean AddMatch(Match oneMatch);
    void ImportMatches(String fileMatches);
    Match GetMatch(Date matchDay, Team home, Team away);
    int HomeGoals();
    int AwayGoals();
    ArrayList<Match> MatchesOfTeam(Team oneTeam);
    int RedCards(Team oneTeam);
    ArrayList<Team> TopRedCards();
	
}

