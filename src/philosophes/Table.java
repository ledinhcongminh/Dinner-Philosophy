package philosophes;

import java.util.ArrayList;
import java.util.Arrays;

import plateform.Environnement;

public class Table extends Environnement {
	int nbFourchettes; //nombre de fourchettes
	boolean[] lesFourchettes;
	


	public Table(int _nbFourchettes){ //Constructeur d'une liste boolean de fourchettes et initialise tous les valeurs à true
		nbFourchettes = _nbFourchettes;
		lesFourchettes = new boolean[nbFourchettes];
		Arrays.fill(lesFourchettes, true);
	}
	

	public synchronized boolean prendre(int i){ //quand un philosopher prend deux fourchettes
		
		int g = i;
		int d = (i+1);
		if (d==Philo.NB_PHILO)
			d=0;
		/**
		while (!lesFourchettes[g] || !lesFourchettes[d]){//vérifier si les fourchettes sont disponibles
			try{
				wait();
			}
			catch (InterruptedException e){}
		}  */
		if (lesFourchettes[g] && lesFourchettes[d]) {
			lesFourchettes[g] = false;
			lesFourchettes[d] = false;
			return true;
		}
		return false;
	}

	
	public synchronized void poser(int i){ //quand un philosopher dépose deux fourchettes
		int g = i;
		int d = i+1;
		if (d==Philo.NB_PHILO)
			d=0;
		lesFourchettes[g] = true;
		lesFourchettes[d] = true;
	    notifyAll(); // ??? attente de fourchettes
	}
	
	public boolean verifier(int i){ //on vérifie si les fourchettes sont disponible ou pas
		return lesFourchettes[i];
	}
		

}
