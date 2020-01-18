package plateform;

import java.util.Arrays;

public class Message {
	private int Permatif;
  	private String Contenu;

    public Message(int _Permatif, String _Contenu) {
        this.Permatif = _Permatif;
        this.Contenu = _Contenu;
    }
  	
    public int getPermatif() {
        return Permatif;
    }
  
    public String getContenu() {
        return Contenu;
    }
  
}
