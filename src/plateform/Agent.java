package plateform;
import java.util.*;
import java.util.Arrays;

public abstract class Agent extends Thread {
	private int nb;
	private Environnement myEnv;
	private boolean running = true;
	
	private int lesMessages[] = new int[] {0, 0, 0, 0, 0}; //la somme des permatifs des messages
	private int boite[] = new int[] {0, 0, 0, 0, 0}; //la somme des lettres
	//Arrays.fill(lesMessages, 0);

	public Agent(int n, Environnement e) {
		nb =n;
		myEnv = e;
		myEnv.addAgent(this);
		this.start();
	}
	
	public int getNb() {
		return nb;
	}

	public int getNbMessages(int _nb) {
		//return lesMessages.get(_nb).size();
		return boite[_nb];
	}
	
	public void viderBoite(int _nb) {
		lesMessages[_nb] = 0;
		boite[_nb] = 0;
	}
	
	
	
	public int getBoite(int _nb){// return la somme des permatifs des lettres
		return lesMessages[_nb];
	}
	
    protected synchronized void sendMessages(Message _m, int _nb) {
    	lesMessages[_nb] += _m.getPermatif();
    	boite[_nb]++;
    }
		
	public Environnement getMyEnv() {
		return myEnv;
	}
	
	public void stopAgent() {
		running = false;
	}

	public void run() {

//		long start = System.currentTimeMillis();
		
		while (running) {
			//Message m = (getBoite()).get((getBoite()).size()-1); //on consulte le dernier message dans la boite
			//Message m = getBoite();
			try { boucle_procedurale(); }
			catch (Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		terminate_agent();
	}
	
	//public abstract void boucle_procedurale(Message m); // éventuellement m=NULL
	public abstract void boucle_procedurale();
	public abstract void terminate_agent();
}


///////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////ANNEXE/////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////


//private ArrayList<Message> lesMessages =new ArrayList<Message>();


//public double getPermatifs(int _nb){
//double sum_permatif = 0;
//for (int j = 1; j < lesMessages.get(_nb).size(); j++) {
//	sum_permatif += lesMessages.get(_nb).get(j).getPermatif();
//}
//return sum_permatif;
//}

//public ArrayList<Message> getBoite(int _nb){
	//return lesMessages.get(_nb); //does not work
	
//	ArrayList<int> innerList = new ArrayList<Message>();
//	
//	
//	for (int j = 0; j < lesMessages.get(_nb).size(); j++) {
//	    innerList.add(lesMessages.get(_nb).get(j).getPermatif());
//	}
//}

//public void viderBoite(int _nb) {
//	lesMessages.get(_nb.clear();
//}


//protected synchronized void sendMessages(Message _m, int _nb) {
//    //lesMessages.get(_nb).add(_m);
//}

//public int getNbMessages(int _nb) {
//	//return lesMessages.get(_nb).size();
//}
