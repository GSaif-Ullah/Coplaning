package com.coplaning.dao;

import java.util.List;

public interface FlightDAO {


	List<FlightContainer> getFlights();

	FlightContainer getFlightContainer(int id);

	//recherche avec depart
	List<FlightContainer> Search(String departure);
	
	//recherche avec depart et une date
	List<FlightContainer> Search(String departure,String date1);
	
	//recherche avec depart et une arrivee
	List<FlightContainer> SearchLieu(String departure,String arrival);
	
	//recherche avec depart entre deux dates
	List<FlightContainer> Search(String departure,String d1,String d2);
	
	//recherche avec depart et une arrivee entre deux dates
	List<FlightContainer> Search(String departure,String arrival,String d1,String d2);
	List<FlightContainer> Search(String departure,String arrival,int sea,String d1,String d2);

	
	List<FlightContainer> Search(String departure,String arrival,int seat);
	List<FlightContainer> Search(String departure,String arrival,int seat,int cost);
	List<FlightContainer> Search(String departure,String arrival,int seat,int cost,int cost1);
 	List<FlightContainer> Search(String departure,String arrival,int seat,int cost,int cost1,String d1,String d2);
 	
	List<FlightContainer> Searchspecial(String cas, String word);
  
	int addFlightContainer(FlightContainer container);
	
	void deleteFlightContainer(int id);
	
	void BookFlight(int id_flight, int id_passager);

	void BookFlightSeat(int id_flight,int seat);

	
	void BookPilot(int id_flight,int id_pilot);
}
