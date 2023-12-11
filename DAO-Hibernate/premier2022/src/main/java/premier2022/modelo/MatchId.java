package premier2022.modelo;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class MatchId implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2076971311197409783L;
	
	private Date date;
    private String homeTeam;
    private String awayTeam;
    
    public MatchId() {}
	
	public MatchId(Date date, String homeTeam, String awayTeam) {
		super();
		this.date = date;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(String awayTeam) {
		this.awayTeam = awayTeam;
	}

	@Override
	public int hashCode() {
		return Objects.hash(awayTeam, date, homeTeam);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MatchId other = (MatchId) obj;
		return Objects.equals(awayTeam, other.awayTeam) && Objects.equals(date, other.date)
				&& Objects.equals(homeTeam, other.homeTeam);
	}
	
	

}
