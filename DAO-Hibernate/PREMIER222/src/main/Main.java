package main;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;

import Model.Team;
import Model.Match;
import dao.DAOManager;
import dao.SeasonDAOFactory;

public class Main {

	final static String CLUB_FILE = "ProjectFiles/epl_clubs_info_2022-23.csv";
	final static String MATCH_FILES = "ProjectFiles/epl_results_2022-23.csv";
	
	public static void main(String[] args) 
	{
		try {
			SeasonDAOFactory fact = new SeasonDAOFactory();
			DAOManager dao = fact.createDAO();

			//ADD_TEAM:
			Team newteam = new Team("Starfort Town", "STC", "#FF0000", "https://example.com/stratford-town-logo.png");
			Team fcGirona = new Team("FC GIRONA", "FCG", "#CC0000", "http://t0.gstatic.com/images?q=tbn:ANd9GcTdVK_HP1VXOCZ1lEUlZx0WPENLJTRR1wj4bMV2p6FVd_op2QdP");
			//dao.AddTeam(fcGirona);
			
			//IMPORT_TEAMS:
			try {
				//dao.ImportTeams(CLUB_FILE);
			} catch (Exception e) {
			
				e.printStackTrace();
			}
			
			//GET_TEAM:
	        Team team = dao.GetTeam("FCG");
			System.out.println(team.toString());
			
			//GET_TEAM_ABBREVIATIONS:
			String clubName = "ARSENAL";
			String teamAbv = dao.GetTeamAbbreviation(clubName);
			System.out.println("Team abbreviation of " +  clubName + ": " + teamAbv);
			
			//ADD_MATCH
			Calendar dateAndTime = Calendar.getInstance();
			dateAndTime.set(2022, 8, 06);
			String div = "E0";
			Date matchDate = new Date(dateAndTime.getTimeInMillis());
			String matchTime = "17:30";
			String homeTeam = "FCG";
			String awayTeam = "MNU";
			int fthg = 2;
			int ftag = 2;
			char ftr = 'H';
			int hthg = 1;
			int htag = 1;
			char htr = 'A';
			String referee = "F Chic"; 
			int hs = 5;
			int as = 6;
			int hst = 2;
			int ast = 2;
			int hf = 10;
			int af = 18;
			int hc = 2;
			int ac = 5;
			int hy = 1;
			int ay = 1;
			int hr = 0;
			int ar = 0;
		    Match match = new Match(div, matchDate, matchTime, homeTeam, awayTeam, fthg, ftag, ftr, hthg, htag, htr, referee, hs, as, hst, ast, hf, af, hc, ac, hy, ay, hr, ar);
			//dao.AddMatch(match);
			
			//IMPORT_MATCH
			try { 
				//dao.ImportMatches(MATCH_FILES);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		
			//GET MATCH. Si és null, no hi ha coincidència. 
			String date = "21/01/2023";
			Match matchFromDB = null;
			Team liverpool = dao.GetTeam("LIV");
			Team chelsea = dao.GetTeam("CHE");
		    java.util.Date matchDateDB = new SimpleDateFormat("dd/MM/yyyy").parse(date);
		    		
		    matchFromDB = dao.GetMatch(matchDateDB, liverpool, chelsea);
		    System.out.println(matchFromDB);
		    
			
	        //HomeGoals
	        int totalHomeGoals = dao.HomeGoals();
	        System.out.println("Total home team goals scored: " + totalHomeGoals);
	        
	        //AwayGoals
	        int totalAwayGoals = dao.AwayGoals();
	        System.out.println("Total away team goals scored: " + totalAwayGoals);

	        //List: Matches of a team
	        Team arsenal = dao.GetTeam("ARS");

	        ArrayList<Match> teamMatches = dao.MatchesOfTeam(arsenal);
	        System.out.println("Matches of " + arsenal.getClubName() + ":");
	        for (Match match1 : teamMatches) {
	            System.out.println(match1.toString());
	        }
	        
	        //RedCard
	        int redCards = dao.RedCards(arsenal);
	        System.out.println("Aggregate of red cards for " + arsenal.getClubName() + ": " + redCards);

	        
	        //TopRedCards - cStmnt made with help of chatgpt.
	        ArrayList<Team> topRedCardsTeams = dao.TopRedCards();
	        System.out.println("Team(s) with the most red cards:");
	        for (Team topRedCardTeam : topRedCardsTeams) {
	            System.out.println(topRedCardTeam.getClubName());
	        }
	        //Closing:
	        dao.close();
		}
		catch (Exception e) {
			System.out.println("TANCAMENT INCORRECTE " + e.getClass().getName());
		}
		
	}

}