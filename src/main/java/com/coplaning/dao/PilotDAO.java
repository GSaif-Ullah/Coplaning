package com.coplaning.dao;

import java.util.List;

public interface PilotDAO{

    long addPilotContainer(PilotContainer container);
    List<PilotContainer> getPilots();

    PilotContainer getPilotContainer(long id);
}