package plateform;

import java.util.ArrayList;

public class Environnement {
	ArrayList<Agent> myAgents = new ArrayList<Agent>();
	
	public void addAgent(Agent a) {
		myAgents.add(a);
	}
}
