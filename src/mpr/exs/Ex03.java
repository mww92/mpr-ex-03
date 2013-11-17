package mpr.exs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
/*
 * Korzystając z mechanizmów kolekcji (w tym z odwzorowań/map) zmodyfikuj
 * poniższy kod tak, żeby na standardowym wyjściu produkować „indeks wystapień
 * słów” w załączonym tekście „Księcia” Machiavellego. Indeks powinien składać się 
 * z linii postaci:
 * 
 * <słowo><tabulacja><w1>, <w2>, <w3>, ..., <wk>
 * 
 * gdzie:
 *   - <słowo> oznacza dowolne słowo wystepujące w tekście
 *   - <tabulacja> oznacza znak tabulacji
 *   - <w1>,...,<wk> oznaczają numery wierszy w tekście, w których słowo występuje
 *   
 * Dodatkowe założenia:
 * 
 *   - Sporządzając indeks pomijamy różnice pomiędzy wielkimi i małymi literami.
 *   - Indeks powinien być posortowany alfabetycznie.
 *   - Słowa w indeksie powinny występować bez powtórzeń.
 *   - Numery wierszy (w rwmach pojedynczego wpisu) powinny być uporządkowane rosnąco (bez powtórzeń).
 * 
 */
public class Ex03 {

	public static void main(String[] args) {
		String fname = "Machiavelli.txt";
		Map<String,Set<Integer>> zbior =new TreeMap <>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			String line = br.readLine();
			int i=0;
			while (line != null) {
				i++;
				// oczyszczamy tekst ze znaków interpunkcyjnych, liczb itp.
				line = line.replaceAll("\\d+|[:,\\.\"\\?!;\\-/]|\\b[XIV]+\\b", " ");
				// usuwamy ewentualne odstępy na początku i na końcu linii
				line = line.replaceAll("^\\s+|\\s+$", "");
				if (!line.matches("^\\s*$")) {
					String[] words = line.split("\\s+");
					for (String w : words) {
						Set<Integer> klucz = new TreeSet<>();
						klucz.add(i);
						zbior.put(w.toLowerCase(), (klucz));
						//System.out.print(w.toLowerCase() + " –– ");
					}
					//System.out.println();
				}
				line = br.readLine();
			}
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Nie mogę otworzyć pliku " + fname);
		} catch (IOException e) {
			System.out.println("Błąd podczas czytania z pliku" + fname);
		}
		
		System.out.println(zbior);
	}

}
