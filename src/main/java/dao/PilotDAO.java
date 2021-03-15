package dao;

import java.util.List;

public interface PilotDAO{
	List<Pilot> ListPilot();	
	
	Pilot getPilot(String Username);	
	
	Pilot getPilot_ID(String ID_Pilot);	
	
	void PutPilot(Pilot p);
	
	void DeletePilot(Pilot P);
	
	void PostPilot(Pilot P);
	
	// L'avion attribué au pilote 
	void PutFlight(Flight F);
	
	void PostFlight(Flight F);
	
	void DeleteFlight(Flight F);

}
