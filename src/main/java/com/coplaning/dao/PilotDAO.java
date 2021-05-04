package com.coplaning.dao;

import java.util.List;

public interface PilotDAO{
	//permet d'ajouter un pilote 
	int addPilotContainer(PilotContainer container);
	
	//recuperer la liste de tous les pilotes
	List<PilotContainer> getPilots();
	
	//recuperer l'id d'un pilote
	PilotContainer getPilotContainer(int id);
	
	//recherche une liste de pilote avec un mot specifique
	List<PilotContainer> Search(String cas, String word);
}
