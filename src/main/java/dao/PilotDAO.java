package dao;

import java.util.List;

public interface PilotDAO{
	List<Pilot> ListPilot();	
	
	Pilot getPilot(String Username);	
	
	Pilot getPilot_ID(String ID_Pilot);	
	
	void putPilot(Pilot p);
	
	void deletePilot(Pilot P);
	
	void postPilot(Pilot P);
	
	// L'avion attribué au pilote 
	void putFlight(Flight F);
	
	void postFlight(Flight F);
	
	void deleteFlight(Flight F);

}
