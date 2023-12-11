package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import Model.Match;
import Model.Team;

public class DAOManagerJDBCImpl implements DAOManager {

	private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/premier2223";
    private String user = "root";
    private String password = "";
    private CallableStatement cStmt;
    private ResultSet rs;
    
	public DAOManagerJDBCImpl() 
	{
		try {
			
			connection = DriverManager.getConnection(url,user, password);

		} catch (SQLException e) {
			e.printStackTrace();
		};
	}
	

	public void close() throws Exception 
	{
		rs.close();
		cStmt.close();
		connection.close();
	}
    
    		
	public boolean AddTeam(Team oneTeam) 
	{
		boolean upDate = false;
		try {
			cStmt = connection.prepareCall("{call ADD_TEAM(?,?,?,?)}");
			cStmt.setString(1, oneTeam.getClubName());
			cStmt.setString(2, oneTeam.getAbv());
			cStmt.setString(3, oneTeam.getHexCode());
			cStmt.setString(4, oneTeam.getLogoLink());
			cStmt.executeUpdate();
			upDate = true;
		}
		catch (SQLException ex) 
		{
			upDate = false;
			System.out.println("Team could not be added: " + ex.getMessage());
		}
		return upDate;
	}
	
	public void ImportTeams(String fileTeams) {
		try {
			
			//Convertim a FILE: Scanner espera rebre classe file.
			File teamsFile = new File(fileTeams);
			Scanner scanner = new Scanner(teamsFile);
			scanner.useDelimiter(",");
			
			//Ens saltem la capçalera.
			scanner.nextLine();
			String line = scanner.nextLine();
			
			//Lectura de la línia:
			while (scanner.hasNextLine() && line != null)
			{
				
				String[] fields = line.split(",");
				String clubName = fields[0].trim();
				String clubAbv = fields[1].trim();
				String clubHexCode = fields[2].trim();
				String clubLogoLink = fields[3].trim();
				
				//Creem equip en base d'info llegida:
				Team team = new Team(clubName, clubAbv, clubHexCode, clubLogoLink);
				
				//Afegim equip a db:
				AddTeam(team);
	            
				cStmt.executeUpdate();
	            
	            line = scanner.nextLine();
				
			}
			scanner.close();
		}
		catch(SQLException | FileNotFoundException ex) {
			System.out.println("An error ocurred while scanning the file. Teams could not be imported correctly: " + ex.getMessage());
		}
	}

	@SuppressWarnings("finally")
	@Override
	public Team GetTeam(String teamAbbreviation) {
		Team team = null;
		try {
			
	        cStmt = connection.prepareCall("{CALL GET_TEAM(?, ?, ?, ?)}");
	        cStmt.setString(1, teamAbbreviation);
	        cStmt.registerOutParameter(2, Types.VARCHAR); // p_clubName
	        cStmt.registerOutParameter(3, Types.VARCHAR); // p_hexCode
	        cStmt.registerOutParameter(4, Types.VARCHAR); // p_logoLink
	        cStmt.execute();
	
	        String clubName = cStmt.getString(2); // Retrieve the value of p_clubName
	        String clubHexCode = cStmt.getString(3); // Retrieve the value of p_hexCode
	        String clubLogoLink = cStmt.getString(4); // Retrieve the value of p_logoLink

	        if (clubName != null) {
	            team = new Team(clubName, teamAbbreviation, clubHexCode, clubLogoLink);
	        }

	    } catch (SQLException ex) {
	    	System.out.println("An error occurred while retrieving the team: " + ex.getMessage());
	    }

		return team;
	}

	@Override
	public String GetTeamAbbreviation(String teamName) {
		String abv = " ";
		try {
			cStmt = connection.prepareCall("{call GET_ABV(?,?)}");
			cStmt.setString(1, teamName);
			cStmt.registerOutParameter(2, Types.VARCHAR);
			cStmt.execute();
			
			abv = cStmt.getString(2);
			
		}
		catch (SQLException ex) {
			System.out.println("An error ocurred while retrieving the abbreviation: " + ex.getMessage());
		}
		return abv;
	
	}

	@Override
	public boolean AddMatch(Match oneMatch) {
		boolean upDate = false;
		System.out.println(oneMatch);
		try {
	        cStmt = connection.prepareCall("{call ADD_MATCH(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
	        cStmt.setString(1, oneMatch.getDiv()); 
	        cStmt.setDate(2, new java.sql.Date(oneMatch.getDate().getTime()));
	        cStmt.setString(3, oneMatch.getTime());
	        cStmt.setString(4, oneMatch.getHomeTeam());
	        cStmt.setString(5, oneMatch.getAwayTeam());
	        cStmt.setInt(6, oneMatch.getFthg());
	        cStmt.setInt(7, oneMatch.getFtag());
	        cStmt.setString(8, String.valueOf(oneMatch.getFtr()));
	        cStmt.setInt(9, oneMatch.getHthg());
	        cStmt.setInt(10, oneMatch.getHtag());
	        cStmt.setString(11, String.valueOf(oneMatch.getHtr()));
	        cStmt.setString(12, oneMatch.getReferee());
	        cStmt.setInt(13, oneMatch.getHs());
	        cStmt.setInt(14, oneMatch.getAs());
	        cStmt.setInt(15, oneMatch.getHst());
	        cStmt.setInt(16, oneMatch.getAst());
	        cStmt.setInt(17, oneMatch.getHf());
	        cStmt.setInt(18, oneMatch.getAf());
	        cStmt.setInt(19, oneMatch.getHc());
	        cStmt.setInt(20, oneMatch.getAc());
	        cStmt.setInt(21, oneMatch.getHy());
	        cStmt.setInt(22, oneMatch.getAy());
	        cStmt.setInt(23, oneMatch.getHr());
	        cStmt.setInt(24, oneMatch.getAr());

	        System.out.println(oneMatch);
			
	        cStmt.executeUpdate();
			
	        upDate = true;
		}
		catch (SQLException ex) 
		{
			System.out.println("Match could not be added: " + ex.getMessage());
			
		}
		return upDate;
	}

	@Override
	public void ImportMatches(String fileMatches) {
		try {
	        File matchFile = new File(fileMatches);
	        Scanner scanner = new Scanner(matchFile);       
	        scanner.useDelimiter(";");
	        scanner.nextLine(); //Saltem capçalera.
	        String line = scanner.nextLine();
	        
	        while (scanner.hasNextLine() && line != null) {
	            System.out.println("Line: " + line); // debug
	            String[] fields = line.split(";");
	            String div = fields[0].trim();

	            String dateString = fields[1].trim();
	            System.out.println(dateString); //debug
 
	            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
	            System.out.println(dateString); //debug
	            
	            String time = fields[2].trim();
	            System.out.println(time);

	            String homeTeam = fields[3].trim();
	            String homeTeamAbv = GetTeamAbbreviation(homeTeam);
	            String awayTeam = fields[4].trim();
	            String awayTeamAbv = GetTeamAbbreviation(awayTeam);
	            int fthg = Integer.parseInt(fields[5].trim());
	            int ftag = Integer.parseInt(fields[6].trim());
	            char ftr = fields[7].trim().charAt(0);
	            int hthg = Integer.parseInt(fields[8].trim());
	            int htag = Integer.parseInt(fields[9].trim());
	            char htr = fields[10].trim().charAt(0);
	            String referee = fields[11].trim();
	            int hs = Integer.parseInt(fields[12].trim());
	            int as = Integer.parseInt(fields[13].trim());
	            int hst = Integer.parseInt(fields[14].trim());
	            int ast = Integer.parseInt(fields[15].trim());
	            int hf = Integer.parseInt(fields[16].trim());
	            int af = Integer.parseInt(fields[17].trim());
	            int hc = Integer.parseInt(fields[18].trim());
	            int ac = Integer.parseInt(fields[19].trim());
	            int hy = Integer.parseInt(fields[20].trim());
	            int ay = Integer.parseInt(fields[21].trim());
	            int hr = Integer.parseInt(fields[22].trim());
	            int ar = Integer.parseInt(fields[23].trim());
	            
	            // Create a Match object
	            Match match = new Match(div, date, time, homeTeamAbv, awayTeamAbv, fthg, ftag, ftr, hthg, htag, htr,
	                    referee, hs, as, hst, ast, hf, af, hc, ac, hy, ay, hr, ar);
	            System.out.println(match);
	            
	           
	            //Prepare the database call statement, using the existing method.
	            AddMatch(match);

	            // Call to read the next Line:
	            line = scanner.nextLine();
	        }
	        scanner.close();
        
	    } catch (FileNotFoundException e) {
	    	 System.out.println("File could not be found: " + e.getMessage());
	    	 
		} catch (ParseException pex) {
			System.out.println("Could not parse: " + pex.getMessage());
		} 
	}

	@Override
	public Match GetMatch(Date matchDay, Team home, Team away) {
		Match match = null;
		try {
			cStmt = connection.prepareCall("{call GET_MATCH(?,?,?}");
			cStmt.setDate(1, new java.sql.Date(matchDay.getTime()));
			cStmt.setString(2, home.getAbv());
			cStmt.setString(3, away.getAbv());
			rs = cStmt.executeQuery();

			if (rs.next()) {
				String matchDiv = rs.getString("match_div");
				Date matchDate = rs.getDate("match_date");
	            String matchTime = rs.getString("match_time");
	            String homeTeamAbv = rs.getString("home_team");
	            String awayTeamAbv = rs.getString("away_team");
	            int fthg = rs.getInt("fthg");
				int ftag = rs.getInt("ftag");
				char ftr = rs.getString("ftr").charAt(0);
				int hthg = rs.getInt("hthg");
				int htag = rs.getInt("htag");
				char htr = rs.getString("htr").charAt(0);
				String referee = rs.getString("referee");
				int hs = rs.getInt("hs");
				int as = rs.getInt("match_as");
				int hst = rs.getInt("hst");
				int ast = rs.getInt("ast");
				int hf = rs.getInt("hf");
				int af = rs.getInt("af");
				int hc = rs.getInt("hc");
				int ac = rs.getInt("ac");
				int hy = rs.getInt("hy");
				int ay = rs.getInt("ay");
				int hr = rs.getInt("hr");
				int ar = rs.getInt("ar");
	            
				match = new Match(matchDiv,matchDate,matchTime,homeTeamAbv,awayTeamAbv,fthg,ftag,ftr,hthg,htag,
						htr,referee,hs,as,hst,ast,hf,af,hc,ac,hy,ay,hr,ar);
			}
		}
		catch (SQLException ex){
			System.out.println("An error ocurred. Match could not be retrieved: " + ex.getMessage());
		}
		return match;

	}

	@Override
	public int HomeGoals() {
		int totalHomeGoals = 0;
		try {
			cStmt = connection.prepareCall("{call GET_HOME_GOALS()}");
	        rs = cStmt.executeQuery();
	        if (rs.next()) {
	            totalHomeGoals = rs.getInt(1);
	        }
		}
        catch(SQLException ex) {
        	System.out.println("An error ocurred. Total home goals could not be retrieved: " + ex.getMessage());
        }
		return totalHomeGoals;
	}

	@Override
	public int AwayGoals() {
		int totalAwayGoals = 0;
		try {
			cStmt = connection.prepareCall("{call GET_AWAY_GOALS()}");
	        rs = cStmt.executeQuery();
	        if (rs.next()) {
	            totalAwayGoals = rs.getInt(1);
	        }
		}
        catch(SQLException ex) {
        	System.out.println("An error ocurred. Total away goals could not be retrieved: " + ex.getMessage());
        }
		return totalAwayGoals;
	}

	@Override//RETOCAR
	public ArrayList<Match> MatchesOfTeam(Team oneTeam)  {
		
		ArrayList<Match> matches = new ArrayList<>();

		try {
	        cStmt = connection.prepareCall("{call GET_MATCHES_OF_TEAM(?)}");
	        cStmt.setString(1, oneTeam.getAbv());

	        rs = cStmt.executeQuery();

	        while (rs.next()) {
	        	
	            // Retrieve the match details from the result set
				String matchDiv = rs.getString("match_div");
				Date matchDate = rs.getDate("match_date");
	            String matchTime = rs.getString("match_time");
	            String homeTeamAbv = rs.getString("home_team");
	            String awayTeamAbv = rs.getString("away_team");
	            int fthg = rs.getInt("fthg");
				int ftag = rs.getInt("ftag");
				char ftr = rs.getString("ftr").charAt(0);
				int hthg = rs.getInt("hthg");
				int htag = rs.getInt("htag");
				char htr = rs.getString("htr").charAt(0);
				String referee = rs.getString("referee");
				int hs = rs.getInt("hs");
				int as = rs.getInt("match_as");
				int hst = rs.getInt("hst");
				int ast = rs.getInt("ast");
				int hf = rs.getInt("hf");
				int af = rs.getInt("af");
				int hc = rs.getInt("hc");
				int ac = rs.getInt("ac");
				int hy = rs.getInt("hy");
				int ay = rs.getInt("ay");
				int hr = rs.getInt("hr");
				int ar = rs.getInt("ar");
				
				
	            
	            // Create a new Match object and add it to the list
	            Match match = new Match(matchDiv,matchDate,matchTime,homeTeamAbv,awayTeamAbv,fthg,ftag,ftr,hthg,htag,
						htr,referee,hs,as,hst,ast,hf,af,hc,ac,hy,ay,hr,ar);
	            matches.add(match);
	        }

	        rs.close();
	        cStmt.close();
	    } catch (SQLException e) {
	        System.out.println("Failed to get matches of team: " + e.getMessage());
	    }

	    return matches;
	}

	@Override
	public int RedCards(Team oneTeam) {
		int redCards = 0;
		 try {
		        cStmt = connection.prepareCall("{call RED_CARDS(?, ?)}");
		        cStmt.setString(1, oneTeam.getAbv());
		        cStmt.registerOutParameter(2, Types.INTEGER);
		        cStmt.execute();
		        redCards = cStmt.getInt(2);
		    } catch (SQLException e) {
		        System.out.println("Failed to get red cards of team: " + e.getMessage());
		    }

		    return redCards;
	}

	@Override
	public ArrayList<Team> TopRedCards() {
		ArrayList<Team> topTeams = new ArrayList<>();

	    try {
	        cStmt = connection.prepareCall("{call TOP_RED_CARDS()}");
	        rs = cStmt.executeQuery();

	        while (rs.next()) {
	            String teamName = rs.getString("club_name");
	            String teamAbv = rs.getString("abv");
	            String hexCode = rs.getString("hex_code");
	            String logoLink = rs.getString("logo_link");

	            Team team = new Team(teamName, teamAbv, hexCode, logoLink);
	            topTeams.add(team);
	        }


	    } catch (SQLException e) {
	        System.out.println("Failed to get teams with top red cards: " + e.getMessage());
	    }

	    return topTeams;
	}
	
	public void Close() {
		
		try {
			rs.close();
			cStmt.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Couldn't close the process correctly: " + e.getMessage());
		}

	}
	
	

}
