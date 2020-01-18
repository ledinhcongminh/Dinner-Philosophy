//package plateform;
//import java.util.*;
//
//public abstract class Agent extends Thread {
//	private int nb;
//	private Environnement myEnv;
//	private boolean running = true;
//	
//	//private int boite = 0;
//	//private Message message;
//	//private int sum_permatif = 0;
//	//private ArrayList<Message> lesMessages =new ArrayList<Message>();
//	private ArrayList<ArrayList<Message>> lesMessages = new ArrayList<ArrayList<Message>>(5);
//
//	public Agent(int n, Environnement e) {
//		nb =n;
//		myEnv = e;
//		myEnv.addAgent(this);
//		this.start();
//	}
//	
//	public int getNb() {
//		return nb;
//	}
//
//	public int getNbMessages(int _nb) {
//		//return boite;
//		return lesMessages.get(_nb).size();
//	}
//	
//	public void viderBoite(int _nb) {
//		//boite=0;
//		//sum_permatif = 0;
//		lesMessages.get(_nb).clear();
//	}
//	
//	public ArrayList<Message> getBoite(int _nb){
//		//return message;
//		//return lesMessages.get(_nb); //does not work
//		
////		ArrayList<int> innerList = new ArrayList<Message>();
////		
////		
////		for (int j = 0; j < lesMessages.get(_nb).size(); j++) {
////		    innerList.add(lesMessages.get(_nb).get(j).getPermatif());
////		}
//		return null;
//	}
//	
//	public double getPermatifs(int _nb){
//		double sum_permatif = 0;
//		for (int j = 1; j < lesMessages.get(_nb).size(); j++) {
//			sum_permatif += lesMessages.get(_nb).get(j).getPermatif();
//		}
//		return sum_permatif;
//	}
//	
//    protected synchronized void sendMessages(Message _m) {
//        lesMessages.get(nb).add(_m);
//    	//this.message = _m;
//    	//boite++;
//    	//sum_permatif += _m.getPermatif();
//    }
//		
//	public Environnement getMyEnv() {
//		return myEnv;
//	}
//	
//	public void stopAgent() {
//		running = false;
//	}
//
//	public void run() {
//
////		long start = System.currentTimeMillis();
//		
//		while (running) {
//			//Message m = (getBoite()).get((getBoite()).size()-1); //on consulte le dernier message dans la boite
//			//Message m = getBoite();
//			try { boucle_procedurale(); }
//			catch (Exception e) {
//				e.printStackTrace();
//				System.exit(1);
//			}
//		}
//		terminate_agent();
//	}
//	
//	//public abstract void boucle_procedurale(Message m); // éventuellement m=NULL
//	public abstract void boucle_procedurale();
//	public abstract void terminate_agent();
//}
