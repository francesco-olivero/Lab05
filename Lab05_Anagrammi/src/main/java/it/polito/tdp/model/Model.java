package it.polito.tdp.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.DAO.AnagrammaDAO;

public class Model {
	List<String> dizionario;
	List<String> anagrammi;
	AnagrammaDAO dao = new AnagrammaDAO();
	
	public List<String> cercaAnagrammi (String word) {
		String parziale = "";
		anagrammi = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(); // vedi riga 29
		recursive(word, word, 0, parziale, sb);
		return anagrammi;
	}
	
	// lettere è l'insieme di lettere su cui itero ad ogni passo, se parola="abc", al passo 1, lettere=parola
	// poi parziale="a" e lettere="bc"
	public void recursive(String lettere, String parola, int level, String parziale, StringBuilder sb) {
		if (level == parola.length() && !this.alreadySaved(parziale)) {
			System.out.println(parziale);
			anagrammi.add(new String(parziale)); // è necessario copiare parziale in una nuova stringa
			return;								 // perché altrimenti copierei solo il riferimento di
												 // di parziale che verrebbe poi modificato nelle iterazioni
												 // successive e sarebbe sempre lo stesso alla fine
		}
		for (int i=0; i<lettere.length(); i++) {
			parziale += lettere.charAt(i);
			sb = new StringBuilder(lettere); // copia StreamBuilder di lettere per usare deleteCharAt()
			sb.deleteCharAt(i);
			recursive(sb.toString(), parola, level + 1, parziale, sb);
			parziale = parziale.substring(0, parziale.length()-1);
		}
	}
	
	// per evitare duplicati nel risultato della ricorsione
	public boolean alreadySaved (String anagr) {
		return anagrammi.contains(anagr);
	}
	
	public void updateDizionario() {
		this.dizionario=this.dao.getDizionario();
	}
	
	/*
	 * controllo se anagramma è presente nel dizionario
	 */
	public boolean isCorrect(String anagramma) {
			if(this.dizionario.contains(anagramma))
				return true;
		return false;
	}
}
