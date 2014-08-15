package de.pali.transliteration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import de.general.transliteration.ITransliterator;

public class PaliHarvardKyotoToDevanagari implements ITransliterator {
	private static final HashMap<String, String> map;
	static {
		map = new HashMap<String, String>();
		map.put("#a","अ");
		map.put("#A","आ");
		map.put("#i","इ");
		map.put("#I","ई");
		map.put("#u","उ");
		map.put("#U","ऊ");
		map.put("#R","ऋ");
		map.put("#RR","ॠ");
		map.put("#lR","ऌ");
		map.put("#lRR","ॡ");
		map.put("#e","ए");
		map.put("#ai","ऐ");
		map.put("#o","ओ");
		map.put("#au","औ");
		map.put("A","ा");
		map.put("i","ि");
		map.put("I","ी");
		map.put("u","ु");
		map.put("U","ू");
		map.put("R","ृ");
		map.put("RR","ॄ");
		map.put("lR","ॢ");
		map.put("lRR","ॣ");
		map.put("e","े");
		map.put("ai","ै");
		map.put("o","ो");
		map.put("au","ौ");
		map.put("halant","्");
		map.put("M","ं");
		map.put("H","ः");
		map.put("","ँ");
		map.put("","ॅ");
		map.put("k","क");
		map.put("kh","ख");
		map.put("g","ग");
		map.put("gh","घ");
		map.put("G","ङ");
		map.put("c","च");
		map.put("ch","छ");
		map.put("j","ज");
		map.put("jh","झ");
		map.put("J","ञ");
		map.put("T","ट");
		map.put("Th","ठ");
		map.put("D","ड");
		map.put("Dh","ढ");
		map.put("N","ण");
		map.put("t","त");
		map.put("th","थ");
		map.put("d","द");
		map.put("dh","ध");
		map.put("n","न");
		map.put("p","प");
		map.put("ph","फ");
		map.put("b","ब");
		map.put("bh","भ");
		map.put("m","म");
		map.put("y","य");
		map.put("r","र");
		map.put("l","ल");
		map.put("v","व");
		map.put("z","श");
		map.put("S","ष");
		map.put("s","स");
		map.put("h","ह");
		map.put("L","ळ");
		map.put("kS","क्ष");
		map.put("jJ","ज्ञ");
		map.put("","क़");
		map.put("","ख़");
		map.put("","ग़");
		map.put("","ज़");
		map.put("","ड़");
		map.put("","ढ़");
		map.put("","फ़");
		map.put("","य़");
		map.put("","ऱ");
		map.put("OM","ॐ");
		map.put("'","ऽ");
		map.put("|","।");
		map.put("||","॥");
		map.put("","॒");
		map.put("","॑");
		map.put("0","०");
		map.put("1","१");
		map.put("2","२");
		map.put("3","३");
		map.put("4","४");
		map.put("5","५");
		map.put("6","६");
		map.put("7","७");
		map.put("8","८");
		map.put("9","९");
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

	@Override
	public String fromSchema()
	{
		return "HKC";
	}

	@Override
	public String toSchema()
	{
		return "DEV";
	}

	@Override
	public String transliterate(String text)
	{
		StringBuilder sb = new StringBuilder();
		// chunk the text
		String[] chunks = chunk(text);
		for (int i = 0; i < chunks.length; i++) {
			// get current chunk
			String c = chunks[i];
			// split into consonant and vowel
			String[] parts = c.split("%");
			String consonant = parts[0];
			String vowel = parts[1];
			// if vowel only
			if (consonant.isEmpty()) {
				// use independent vowel symbol
				sb.append(map.get("#"+vowel));
			} else {
				// map consonant
				String cons = map.get(consonant);
				if (cons == null) {
					// if we cannot map consonant, split consonant group
					String first = consonant.substring(0,1);
					String second = consonant.substring(1);
					sb.append(map.get(first));
					// add halant to remove inherent vowel from first consonant
					sb.append(map.get("halant"));
					sb.append(map.get(second));
				} else {
					sb.append(cons);
				}
				// if vowel is not empty and not "a"
				if (!vowel.trim().isEmpty() && !isInherentVowel(vowel)) {
					// add dependent vowel symbol
					sb.append(map.get(vowel));
				}
			}
		}
		return sb.toString();
	}	

	/**
	 * Checks whether the given vowel corresponds to the
	 * inherent devanagari vowel (which is often transliterated as "a")
	 * @param letter letter to check
	 * @return whether given letter is inherent vowel
	 */
	private boolean isInherentVowel(String letter) {
		return letter.equals("a");
	}

	/**
	 * Chunks a text into chunks consisting of
	 * optionally one or more consonants followed by one vowel
	 * @param text
	 * @return
	 */
	private String[] chunk(String text) {
		List<String> out = new ArrayList<String>();
		String[] a = text.split("");
		int j = 1;
		for (int i = 1; i < a.length-1; i++) {
			StringBuilder chunker = new StringBuilder();
			while (isConsonant(a[i])) {
				chunker.append(a[i]);
				i++;
			}
			chunker.append("%"+a[i]);
			out.add(chunker.toString());
			j = i;
		}
		if (++j < a.length) {
			StringBuilder chunker = new StringBuilder();
			while (j < a.length) {
				chunker.append(a[j]);
				j++;
			}
			out.add(chunker.append("% ").toString());
		}
		return out.toArray(new String[1]);
	}

	private boolean isConsonant(String letter) {
		return letter.matches("[^iueoaIUEOA]+");
	}
}
