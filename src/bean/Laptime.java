package bean;

public class Laptime {

	private int raceId ; // refers to {@link Race}
	private int driverId ; // referst to {@link Driver}
	private int lap ;
	// NOT: only the combination of the 3 fields (raceId, driverId, lap) is guaranteed to be unique
	private int position ;
	private String time ; // printable version of lap time
	private int miliseconds ; // numerical version, sutable for computations
	
	
}
