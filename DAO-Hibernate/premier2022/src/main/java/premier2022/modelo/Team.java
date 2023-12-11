package premier2022.modelo;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "team")
public class Team implements Serializable{
	
	private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "club_Name")
    private String clubName;

    @Id
	@Column(name = "abv")
    private String abv;
    
    @Column(name = "hex_code")
    private String hexCode;
    
    @Column(name = "logo_link")
    private String logoLink;
    
    public Team() {}
    
    public Team(String clubName, String abv, String hexCode, String logoLink) {
		this.clubName = clubName;
		this.abv = abv;
		this.hexCode = hexCode;
		this.logoLink = logoLink;
	}
    
    @ManyToMany
    @JoinTable(
        name = "team_match",
        joinColumns = @JoinColumn(name = "team_id"),
        inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    private List<Match> matches;
    

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
		return "Team [club_name=" + clubName + ", abv=" + abv + ", hexCode=" + hexCode + ", logoLink=" + logoLink
				+ "]";
	}

}
