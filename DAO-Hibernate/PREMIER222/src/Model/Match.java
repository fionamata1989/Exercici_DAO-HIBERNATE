package Model;

import java.util.Date;

public class Match {
	
	
	public Match(String matchDiv, Date date, String time, String homeTeam, String awayTeam, int fthg, int ftag, char ftr,
			int hthg, int htag, char htr, String referee, int hs, int as, int hst, int ast, int hf, int af, int hc,
			int ac, int hy, int ay, int hr, int ar) {
		super();
		
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
	private String matchDiv;      //0
	private Date date;
	private String time;       //2
	private String homeTeam;
	private String awayTeam; //4
	private int fthg;
	private int ftag;        //6
	private char ftr;
	private int hthg;		 //8
	private int htag;        
	private char htr;		 //10
	private String referee;
	private int hs;          //12
	private int as;
	private int hst;         //14
	private int ast;
	private int hf;          //16
	private int af;
	private int hc;          //18
	private int ac;           
	private int hy;          //20
	private int ay;
	private int hr;          //22
	private int ar;
	
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
}
