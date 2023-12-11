package test;

import java.sql.Date;

import hibernate.HIBERNATEManager;
import premier2022.modelo.Match;
import premier2022.modelo.MatchId;
import premier2022.modelo.Team;

public class mainTest {

	public static void main(String[] args) {
		try(HIBERNATEManager manager = new HIBERNATEManager("ORMLeague")){
			
			//CRUD TEAM 
			Team team = new Team();
			team.setClubName("Sunderland AFC");
			team.setAbv("SFC");
			team.setHexCode("#FF6600");
			team.setLogoLink("https://example.com/sunderlandafc_logo.png");
			
			System.out.println(manager.AddTeam(team));
			
			String clubAbv = "SFC";
			System.out.println(manager.GetTeam(clubAbv));
			
			manager.UpdateTeam("Sunderland AFC", "SFC", "#FF6600", "https://upload.wikimedia.org/wikipedia/en/7/77/Logo_Sunderland.svg");
			System.out.println(manager.GetTeam(clubAbv));
			
			manager.DeleteTeam(clubAbv);
			
			//CRUD MATCH 
			String div = "E1"; 
			Date matchDate = new Date(2022, 8, 1); 
			String matchTime = "15:00"; 
			String homeTeam = "SFC"; 
			String awayTeam = "ARS"; 

			int homeGoals = 2; 
			int awayGoals = 3; 
			char fullTimeResult = '-'; 
			int halfTimeHomeGoals = 1; 
			int halfTimeAwayGoals = 1; 
			char halfTimeResult = '-'; 
			String referee = "F Chic"; 

			int homeShots = 2; 
			int awayShots = 3; 
			int homeShotsOnTarget = 5; 
			int awayShotsOnTarget = 4; 
			int homeFouls = 0; 
			int awayFouls = 0; 
			int homeCorners = 5; 
			int awayCorners = 4; 
			int homeYellowCards = 0; 
			int awayYellowCards = 0; 
			int homeRedCards = 0; 
			int awayRedCards = 0; 

			Match match = new Match(div, matchDate, matchTime, homeTeam, awayTeam, homeGoals, awayGoals, fullTimeResult,
			                        halfTimeHomeGoals, halfTimeAwayGoals, halfTimeResult, referee, homeShots, awayShots,
			                        homeShotsOnTarget, awayShotsOnTarget, homeFouls, awayFouls, homeCorners, awayCorners,
			                        homeYellowCards, awayYellowCards, homeRedCards, awayRedCards);
			
			if (manager.AddMatch(match)) 
				System.out.println("Match added.");
			else
				System.out.println("Match could not be added.");
			
			MatchId matchId = new MatchId();
			matchId.setDate(matchDate);
			matchId.setHomeTeam("SFC");
			matchId.setAwayTeam("ARS");

			System.out.println(manager.GetMatch(matchId));
			
			String newTime = "16:00";
			int newAwayGoals = 2;
			manager.UpdateMatch(matchTime, matchDate, "16:00", homeTeam, awayTeam, newAwayGoals, fullTimeResult, halfTimeResult, halfTimeHomeGoals, halfTimeAwayGoals, halfTimeResult, referee, homeShots, awayShots, homeShotsOnTarget, awayShotsOnTarget, homeFouls, awayFouls, homeCorners, awayCorners, homeYellowCards, awayYellowCards, homeRedCards, awayRedCards);
			System.out.println(manager.GetMatch(matchId));
			
			manager.DeleteMatch(matchId);
			
			//CLOSE
			manager.close();
		}
	}

}
