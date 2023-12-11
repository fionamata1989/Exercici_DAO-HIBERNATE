package premier2022.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "match")
public class Match implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
    private MatchId id;
	
	@Column(name = "match_div")
	private String matchDiv; //0
	@Column(name = "match_date")
	private Date date;
	@Column(name = "match_time")
	private String time;       //2
	@Column(name = "home_team")
	private String homeTeam;
	@Column(name = "away_team")
	private String awayTeam; //4
	@Column(name = "fthg")
	private int fthg;
	@Column(name = "ftag")
	private int ftag;        //6
	@Column(name = "ftr")
	private char ftr;
	@Column(name = "hthg")
	private int hthg;		 //8
	@Column(name = "htag")
	private int htag;   
	@Column(name = "htr")
	private char htr;		 //10
	@Column(name = "referee")
	private String referee;
	@Column(name = "hs")
	private int hs;          //12
	@Column(name = "match_as")     
	private int as;
	@Column(name = "hst")
	private int hst;         //14
	@Column(name = "ast")
	private int ast;
	@Column(name = "hf")
	private int hf;          //16
	@Column(name = "af")
	private int af;
	@Column(name = "hc")
	private int hc;          //18
	@Column(name = "ac")
	private int ac;           
	@Column(name = "hy")
	private int hy;          //20
	@Column(name = "ay")
	private int ay;
	@Column(name = "hr")
	private int hr;          //22
	@Column(name = "ar")
	private int ar;
	
	public Match() {}
	
	public Match(String matchDiv, Date date, String time, String homeTeam, String awayTeam, int fthg, int ftag, char ftr,
			int hthg, int htag, char htr, String referee, int hs, int as, int hst, int ast, int hf, int af, int hc,
			int ac, int hy, int ay, int hr, int ar) {

		
		this.matchDiv = matchDiv;
		this.date = date;
		this.time = time;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.fthg = fthg;
		this.ftag = ftag;
		this.ftr = ftr;
		this.hthg = hthg;
		this.htag = htag;
		this.htr = htr;
		this.referee = referee;
		this.hs = hs;
		this.as = as;
		this.hst = hst;
		this.ast = ast;
		this.hf = hf;
		this.af = af;
		this.hc = hc;
		this.ac = ac;
		this.hy = hy;
		this.ay = ay;
		this.hr = hr;
		this.ar = ar;
	}

	public String getDiv() {
		return matchDiv;
	}
	public void setDiv(String div) {
		this.matchDiv = div;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
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
	public int getFthg() {
		return fthg;
	}
	public void setFthg(int fthg) {
		this.fthg = fthg;
	}
	public int getFtag() {
		return ftag;
	}
	public void setFtag(int ftag) {
		this.ftag = ftag;
	}
	public char getFtr() {
		return ftr;
	}
	public void setFtr(char ftr) {
		this.ftr = ftr;
	}
	public int getHthg() {
		return hthg;
	}
	public void setHthg(int hthg) {
		this.hthg = hthg;
	}
	public int getHtag() {
		return htag;
	}
	public void setHtag(int htag) {
		this.htag = htag;
	}
	public char getHtr() {
		return htr;
	}
	public void setHtr(char htr) {
		this.htr = htr;
	}
	public String getReferee() {
		return referee;
	}
	public void setReferee(String referee) {
		this.referee = referee;
	}
	public int getHs() {
		return hs;
	}
	public void setHs(int hs) {
		this.hs = hs;
	}
	public int getAs() {
		return as;
	}
	public void setAs(int as) {
		this.as = as;
	}
	public int getHst() {
		return hst;
	}
	public void setHst(int hst) {
		this.hst = hst;
	}
	public int getAst() {
		return ast;
	}
	public void setAst(int ast) {
		this.ast = ast;
	}
	public int getHf() {
		return hf;
	}
	public void setHf(int hf) {
		this.hf = hf;
	}
	public int getAf() {
		return af;
	}
	public void setAf(int af) {
		this.af = af;
	}
	public int getHc() {
		return hc;
	}
	public void setHc(int hc) {
		this.hc = hc;
	}
	public int getAc() {
		return ac;
	}
	public void setAc(int ac) {
		this.ac = ac;
	}
	public int getHy() {
		return hy;
	}
	public void setHy(int hy) {
		this.hy = hy;
	}
	public int getAy() {
		return ay;
	}
	public void setAy(int ay) {
		this.ay = ay;
	}
	public int getHr() {
		return hr;
	}
	public void setHr(int hr) {
		this.hr = hr;
	}
	public int getAr() {
		return ar;
	}
	public void setAr(int ar) {
		this.ar = ar;
	}
	@Override
	public String toString() {
		return "Match [div=" + matchDiv + ", date=" + date + ", time=" + time + ", homeTeam=" + homeTeam + ", awayTeam="
				+ awayTeam + ", fthg=" + fthg + ", ftag=" + ftag + ", ftr=" + ftr + ", hthg=" + hthg + ", htag=" + htag
				+ ", htr=" + htr + ", referee=" + referee + ", hs=" + hs + ", as=" + as + ", hst=" + hst + ", ast="
				+ ast + ", hf=" + hf + ", af=" + af + ", hc=" + hc + ", ac=" + ac + ", hy=" + hy + ", ay=" + ay
				+ ", hr=" + hr + ", ar=" + ar + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(ac, af, ar, as, ast, awayTeam, ay, date, ftag, fthg, ftr, hc, hf, homeTeam, hr, hs, hst,
				htag, hthg, htr, hy, id, matchDiv, referee, time);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Match other = (Match) obj;
		return ac == other.ac && af == other.af && ar == other.ar && as == other.as && ast == other.ast
				&& Objects.equals(awayTeam, other.awayTeam) && ay == other.ay && Objects.equals(date, other.date)
				&& ftag == other.ftag && fthg == other.fthg && ftr == other.ftr && hc == other.hc && hf == other.hf
				&& Objects.equals(homeTeam, other.homeTeam) && hr == other.hr && hs == other.hs && hst == other.hst
				&& htag == other.htag && hthg == other.hthg && htr == other.htr && hy == other.hy
				&& Objects.equals(id, other.id) && Objects.equals(matchDiv, other.matchDiv)
				&& Objects.equals(referee, other.referee) && Objects.equals(time, other.time);
	}
	
	
}


