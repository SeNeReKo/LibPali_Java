package de.pali;


import de.general.transliteration.*;

import de.pali.transliteration.*;


/**
 *
 * @author knauth
 */
public class PaliTransliterationMgr extends TransliterationManager
{

	////////////////////////////////////////////////////////////////
	// Constants
	////////////////////////////////////////////////////////////////

	////////////////////////////////////////////////////////////////
	// Variables
	////////////////////////////////////////////////////////////////

	public static final PaliTransliterationMgr Instance = new PaliTransliterationMgr();

	////////////////////////////////////////////////////////////////
	// Constructors
	////////////////////////////////////////////////////////////////

	/**
	 * Constructor.
	 */
	public PaliTransliterationMgr()
	{
		register(new PaliHarvardKyotoToLatin());
		register(new PaliLatinToHarvardKyoto());
	}

	////////////////////////////////////////////////////////////////
	// Methods
	////////////////////////////////////////////////////////////////

}
