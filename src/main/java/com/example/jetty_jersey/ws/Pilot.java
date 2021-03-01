package com.example.jetty_jersey.ws;

import java.util.List;

public class Pilot {
	public String Username;
	public String Password;
	public String Email;
	public String ID_pilot;
	public List<Flight> flights;
	public List<Pilot> AllPilot;
	
	public interface PilotDAO {
		/**
		 * @return this list of Pilot
		 */
		List<Pilot> ListPilot();
		
		/**
		 * @param Username
		 * @param ID_Pilot
		 * @return the Pilot to a specific Username or ID_Pilot
		 */
		Pilot getPilot(String Username);
		
		Pilot getPilot_ID(String ID_Pilot);
	
		/**
		 * Add a new  Pilot 
		 * @param Pilot
		 */
		void PutPilot(Pilot NewPilot);
		/**
		 * Delete the Pilot 
		 * @param Pilot
		 */
		void DeletePilot(Pilot P);
		/**
		 * Modify a Pilot
		 * @param Pilot
		 */
		void PostPilot(Pilot P);
		/**
		 * Add a new flight
		 * @param Flight
		 */
		void PutFlight(Flight F);
		
		/**
		 * Modify a flight
		 * @param Flight
		 */
		void PostFlight(Flight F);
				/**
		 * Delete a flight
		 * @param Flight
		 */
		void DeleteFlight(Flight F);
	}
}
