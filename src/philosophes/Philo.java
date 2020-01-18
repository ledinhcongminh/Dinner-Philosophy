package philosophes;

import java.util.ArrayList;
import java.util.List;

import plateform.Agent;
import plateform.Message;


public class Philo extends Agent {

	public static final int DELAY = 10;
	public static final int NB_PHILO = 5; //nombre de philosophes et fourchettes

	private int cptFaim; //envie de manger
	private int cptPenser; //envie de penser
	private boolean fourchettes_acquises = false; // true si je tiens les 2 fourchettes
	
	private long start_attendre = -1; // un compteur pour savoir combien de temps un philosophe attend pour chaque fois
	private long total_attente = 0; // un compteur pour savoir combien de temps un philosophe attend pour tous les processus
	
	//private Message m;

	public Philo(int _i, int _cptFaim, int _cptPenser, Table _lesFourchettes){//Créateur
		super(_i,_lesFourchettes);
		cptFaim = _cptFaim;
		cptPenser = _cptPenser;
	}

	public void boucle_procedurale(){
		
		if (this.cptFaim>=this.cptPenser && !fourchettes_acquises){
			if (start_attendre==-1)
				start_attendre=System.currentTimeMillis();
			//1. les Philos ont envie de manger
			System.out.println(this.getName() + " vérifie les fourchettes");
			int nb = getNb();
			boolean fg = ((Table)getMyEnv()).verifier(nb);
			nb++;
			if (nb==NB_PHILO)
				nb=0;
			boolean fd = ((Table)getMyEnv()).verifier(nb);
			

	

			if (!fg||!fd){//vérifier si les fourchettes sont disponibles, s'ils sont pas libres:
				
                ////ENVOYER MESSAGE///
				//Si le Philo a attendu longtemps et s'il a trop faim, il envoie une message
				if ((this.cptFaim >= 6) && (System.currentTimeMillis() - start_attendre > 10)){
					Message madd = new Message(2, "J'ai faim et depuis longtemps j'attends!");
					System.out.println(this.getName() + " a envoye une lettre forte");
					sendMessages(madd, nb);
				}else if (this.cptFaim >= 6){//Si le Philosophe a trop faim, il envoie une message � la boite de lettre
					Message madd = new Message(1, "J'ai faim. Pouvez-vous me donner une access svp?");
					System.out.println(this.getName() + " a envoye une lettre");
					sendMessages(madd, nb);
					//System.out.println(getBoite().size());
				}
				
				///CONSULTER MESSAGE///
				int sum_permatif = getBoite(nb); //on consulte la boite de lettre pour ce philo

				
				if (sum_permatif >= 5){//si le philo a envoyé assez de demande, il n'a pas besoin d'attendre
					System.out.println("'Oui Monsieur " + this.getName() + ", vous pouvez avoir une accès priopritaire'");
					viderBoite(nb); //demande a été acceptée alors on vide sa boite de lettres
				} else { //sinon il pense et attend
                  
                    try{
                  		Thread.sleep(DELAY);
                      	this.cptPenser--; //les philo perdent une pensée
						System.out.println("'Non désolé Monsieur "+ this.getName() +", vous devez penser et encore attendre'");
						this.cptFaim+=1;
                    }
                    catch(InterruptedException e){
                        System.out.println("main thread interrupted");
                    }
                  
				}
				
			} else {
				
				//le Philo croit qu'il peut prendre les fourchettes. Il essaye et il mange si ça marche
				System.out.println(this.getName() + " prend les fourchettes");
				fourchettes_acquises = ((Table)getMyEnv()).prendre(getNb()); 
				if (fourchettes_acquises) {
					long end = System.currentTimeMillis();
					System.out.println(this.getName() + " a attendu pendant " + (end-start_attendre));
					total_attente += (end-start_attendre);
					start_attendre = -1;
				}
			}
		}
		else if (this.cptFaim>=this.cptPenser && fourchettes_acquises) {
			//2. les philos mangent
			System.out.println(this.getName() + " mange");
			this.cptFaim--;
			this.cptPenser++;
		}
		else if (this.cptFaim<this.cptPenser && fourchettes_acquises) {
			//3. les Philo n'ont plus de faim, ils veulent penser
			System.out.println(this.getName() + " n'a plus faim et a envie de penser");
			((Table)getMyEnv()).poser(getNb()); //libérer les fourchettes;
			fourchettes_acquises = false;
		}
		else /* if (this.cptFaim<this.cptPenser && !fourchettes_acquises) */ {
			//4. les Philo pensent et finissent le boucle
			System.out.println(this.getName() + " pense");
			this.cptPenser--;
			this.cptFaim++;
		}
	}
	
	@Override
	public void terminate_agent() {
		System.out.println("L'agent "+getName()+" a attendu en tout "+total_attente);
	}
	
	public static void main(String[] args){

		Table lesFourchettes = new Table(NB_PHILO);
		Philo[] lesPhilosophes = new Philo[NB_PHILO];
		//Création des philosophes
		long Depart = System.currentTimeMillis();
		for (int i=0; i<NB_PHILO;i++){
			lesPhilosophes[i] = new Philo(i, NB_PHILO+1, NB_PHILO, lesFourchettes); 
			//défini un philosopher avec son niveau de faim et envie de penser
		}
		try { Thread.sleep(10); }
		catch (InterruptedException e) { e.printStackTrace(); }
		for(Philo p: lesPhilosophes)//arrête des agents après 1000s
			p.stopAgent();
		long Fin = System.currentTimeMillis();
		double duree = (Fin -  Depart); //le temps total du repas

		System.out.println("Le repas est fini en "+ duree);
		for(int i=0;i<NB_PHILO;i++)
			System.out.println("fourchette "+i+" = "+lesFourchettes.lesFourchettes[i]);
	}
}
