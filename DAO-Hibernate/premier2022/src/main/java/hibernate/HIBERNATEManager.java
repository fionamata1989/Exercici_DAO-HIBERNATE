package hibernate;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import premier2022.modelo.Match;
import premier2022.modelo.MatchId;
import premier2022.modelo.Team;

public class HIBERNATEManager implements AutoCloseable {
	
	private EntityManager manager;
	private EntityManagerFactory factory;
	private EntityTransaction transaction;

	private CriteriaBuilder builder;
	private CriteriaQuery<Team> teamCriteria;
	private CriteriaQuery<Match> matchCriteria;
	private Root<Team> teamRoot;
	private Root<Match> matchRoot;
	
	public HIBERNATEManager(String persistenceUnit)
	{
		factory = Persistence.createEntityManagerFactory(persistenceUnit);
		manager = factory.createEntityManager();
		transaction = null;
		
		builder = manager.getCriteriaBuilder();
		teamCriteria = builder.createQuery(Team.class);
		matchCriteria = builder.createQuery(Match.class);
		teamRoot = teamCriteria.from(Team.class);
		matchRoot = matchCriteria.from(Match.class);
		
		matchCriteria.multiselect(teamRoot);
		teamCriteria.multiselect(matchRoot);
	}
	
	
	//TEAM CRUD
	
	public boolean AddTeam(Team team)
	{
		boolean updated = false;
		EntityTransaction transaction = null;
		
		try
		{
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(team);
			transaction.commit();
			updated = true;
		}
		catch (Exception e)
		{
			transaction.rollback();
			System.out.println("Could not add team." + e.getMessage());
		}
		return updated;
	}
	
	public boolean UpdateTeam(String clubName, String clubAbv, String clubHexCode, String clubLogoLink)
	{
		boolean updated = false;

		try {
			
			transaction = manager.getTransaction();
			transaction.begin();
			
			Team team = manager.find(Team.class, clubAbv);
			
			if (team == null) throw new Exception("Team not found.");
			
			//We don't allow the change of ABV, since it's primary key.
			team.setClubName(clubName);
			team.setHexCode(clubHexCode);
			team.setLogoLink(clubLogoLink);
			
			manager.persist(team);
			transaction.commit();
			updated = true;
			
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Could not update team." + e.getMessage());
		}
		
		return updated;
	}
	
	public boolean DeleteTeam (String clubAbv)
	{
		boolean updated = false;
		
		try {
			
			transaction = manager.getTransaction();
			transaction.begin();
			
			Team team = manager.find(Team.class, clubAbv);
			
			if (team == null) throw new Exception("Team not found.");
			
			manager.remove(team);
			
			transaction.commit();
			updated = true;
			
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Could not delete team." + e.getMessage());
		}
		return updated;
	}
	
	public Team GetTeam (String clubAbv) {
		
		Team team = null;
		
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			
			team = manager.find(Team.class, clubAbv);
			if (team == null) throw new Exception ("Team not found.");
			
			transaction.commit();
			
		} catch (Exception e) {
			
			transaction.rollback();
			System.out.println("Could not recover team." + e.getMessage());
		}
		return team;
	}
	
	//CRUD MATCH
	
	public boolean AddMatch(Match match)

	{
		boolean updated = false;
		EntityTransaction transaction = null;
		
		try {
			
			transaction = manager.getTransaction();
			transaction.begin();
			manager.persist(match);
			
			transaction.commit();
			updated = true;
			
		} catch (Exception e) {
			
			transaction.rollback();
			System.out.println("Could not add match." + e.getMessage());
		}
		return updated;
	}
	
	public boolean UpdateMatch(String matchDiv, Date date, String time, String homeTeam, String awayTeam, int fthg, 
			int ftag, char ftr, int hthg, int htag, char htr, String referee, int hs, int as, int hst, int ast, 
			int hf, int af, int hc, int ac, int hy, int ay, int hr, int ar) {
		
		boolean updated = false;
		
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			
			MatchId matchId = new MatchId();
			matchId.setDate(date);
			matchId.setHomeTeam(homeTeam);
			matchId.setAwayTeam(awayTeam);

			Match match = manager.find(Match.class, matchId);
			
			//We don't allow the change of matchDate, awayTeam or homeTeam, since they're primary key.
			match.setDiv(matchDiv);
			match.setTime(time);
			match.setFthg(fthg);
			
			match.setFtag(ftag);
			match.setFtr(ftr);
			match.setHthg(hthg);
			match.setHtag(htag);
			match.setFtr(ftr);
			match.setReferee(referee);
			match.setHs(hs);
			match.setAs(as);
			match.setHst(hst);
			match.setAst(ast);
			
			match.setHf(hf);
			match.setAf(af);
			match.setHc(hc);
			match.setAc(ac);
			match.setHy(hy);
			match.setAy(ay);
			match.setHr(hr);
			match.setAr(ar);
			
			manager.persist(match);
			transaction.commit();
			updated = true;
			
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Could not update match." + e.getMessage());
		}
		return updated;
		
		}
	
	public boolean DeleteMatch(MatchId matchId)
	{
		boolean updated = false;
		
		try {
			
			transaction = manager.getTransaction();
			transaction.begin();
			
			Match match = manager.find(Match.class, matchId);
			
			if (match == null) throw new Exception("Match not found.");
			
			manager.remove(match);
			
			transaction.commit();
			updated = true;
			
		} catch (Exception e) {
			transaction.rollback();
			System.out.println("Could not delete match." + e.getMessage());
		}
		return updated;
	}
	
	public Match GetMatch (MatchId matchId) {
		
		Match match = null;
		
		try {
			transaction = manager.getTransaction();
			transaction.begin();
			
			match = manager.find(Match.class, matchId);
			if (match == null) throw new Exception("Match not found.");
			
			transaction.commit();
			
		} catch (Exception e) {
			
			transaction.rollback();
			System.out.println("Could not recover team." + e.getMessage());
		}
		return match;
	}
	public void close() {
		manager.close();
		factory.close();
		
	}
}
	
	

