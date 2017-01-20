package application;

import java.util.List;

import bean.Circuit;
import bean.Driver;
import bean.Race;
import bean.Season;
import db.Dao;

public class Model {
	
	private Dao dao =new Dao();
	
	public List<Season> getAnni(){
		List<Season> anni = dao.getAnni();
		return anni;
				
	}
	
	public List<Circuit> getCircuitiAnno(int anno){
		List<Circuit> all= dao.getCircuitiDellAnno(anno);
		return all;
	}
	

	public List<Race> getInfo(int anno, int idCirc){
		List<Race> all= dao.getInfoGare(anno, idCirc);
		return all;
	}
	
	public List<Driver> getPiloti(int anno, int idCircuito){
		List<Driver> all= dao.getPilotiGara(anno, idCircuito);
		return all;
	}
}
