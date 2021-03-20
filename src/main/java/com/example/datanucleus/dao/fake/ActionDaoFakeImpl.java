package com.example.datanucleus.dao.fake;

import java.util.ArrayList;
import java.util.List;

import com.example.datanucleus.dao.Action;
import com.example.datanucleus.dao.ActionDao;

public class ActionDaoFakeImpl implements ActionDao {

	public List<Action> getActions(String username) {
		Action action = new Action();
		action.setUsername(username);
		action.setTitle("Do something");
		action.setContent("A fake content");

		List<Action> result = new ArrayList<Action>();
		result.add(action);

		return result;
	}

	public void addAction(Action action) {
	}

}
