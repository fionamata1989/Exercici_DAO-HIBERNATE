package Model;

public class Team {

	private String clubName;
	private String abv;
	private String hexCode;
	private String logoLink;
	
	public Team(String clubName, String abv, String hexCode, String logoLink) {
		super();
		this.clubName = clubName;
		this.abv = abv;
		this.hexCode = hexCode;
		this.logoLink = logoLink;
	}

	
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getAbv() {
		return abv;
	}
	public void setAbv(String abv) {
		this.abv = abv;
	}
	public String getHexCode() {
		return hexCode;
	}
	public void setHexCode(String hexCode) {
		this.hexCode = hexCode;
	}
	public String getLogoLink() {
		return logoLink;
	}
	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}
	
	@Override
	public String toString() {
		return "Team [clubName=" + clubName + ", abv=" + abv + ", hexCode=" + hexCode + ", logoLink=" + logoLink + "]";
	}

}
