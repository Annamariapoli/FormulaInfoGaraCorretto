package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Circuit;
import bean.Driver;
import bean.Race;
import bean.Season;

public class Dao {
	
	public List<Season> getAnni(){
		List<Season> anni = new LinkedList<Season>();
		String query ="select *  from seasons s  order by s.year DESC";
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Season s = new Season(res.getInt("year"), res.getString("url"));
				anni.add(s);
			}
			conn.close();
			return anni;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Circuit> getCircuitiDellAnno(int year){
		List<Circuit> circuiti = new LinkedList<>();
		String query ="select c.circuitId, c.circuitRef, c.name, c.location, c.country, c.lat, c.lng, c.alt, c.url "
				+ "from races r, circuits c , seasons s   "
				+ "where s.year=r.year and s.year=? and r.circuitId=c.circuitId";
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, year);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Circuit c = new Circuit(res.getInt("circuitId"), res.getString("circuitRef"), res.getString("name"), res.getString("location"), res.getString("country"), res.getFloat("lat"), res.getFloat("lng"), res.getInt("alt"), res.getString("url"));
				circuiti.add(c);
			}
			conn.close();
			return circuiti;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	public List<Race> getInfoGare(int anno,int circuitId ){
		String query="select r.raceId, r.year, r.round, r.circuitId, r.name, r.date, r.time, r.url  "
				+ "from races r , circuits c   "
				+ "where r.circuitId=c.circuitId and r.year=? and c.circuitId=?";
		List<Race> info = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, anno);
			st.setInt(2, circuitId);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Race r = new Race(res.getInt("raceId"), res.getInt("year"), res.getInt("round"), res.getInt("circuitId"), res.getString("name"), res.getDate("date").toLocalDate(), res.getTime("time").toLocalTime(),res.getString("url"));
				info.add(r);
			}
			conn.close();
			return info;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}
	
	
	//piloti ke hanno partecipato alla gara in quel circuito in quell anno
	
	public List<Driver> getPilotiGara(int anno, int circuitoId){
		String query="select distinct d.driverId, d.driverRef, d.number,  d.code, d.forename, d.surname, d.dob, d.nationality, d.url  "
				+ "from races r , laptimes l, drivers d   "
				+ "where r.raceId=l.raceId and d.driverId=l.driverId and r.year=? and r.circuitId=?";
		List<Driver> info = new LinkedList<>();
		Connection conn = DBConnect.getConnection();
		try{
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, anno);
			st.setInt(2, circuitoId);
			ResultSet res = st.executeQuery();
			while(res.next()){
				Driver d = new Driver(res.getInt("driverId"), res.getString("driverRef"), res.getInt("number"), res.getString("code"), res.getString("forename"), res.getString("surname"), res.getDate("dob").toLocalDate(),res.getString("nationality"),res.getString("url"));
				info.add(d);
			}
			conn.close();
			return info;
		}catch(SQLException e ){
			e.printStackTrace();
			return null;
		}
	}

}
