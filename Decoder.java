


public class Decoder {
	
	/**
	 * Takes in one individual bit-string as a parameter
	 * and converts it from binary to character
	 */
	public StringBuilder decode(StringBuilder bitString)
	{
		int currentInteger = 0;
		char currentChar; 
		StringBuilder decodedCharacters = new StringBuilder("");
		
	    for(int j = 0; j < bitString.length(); j += 7)
		{
				
		    currentInteger = Integer.parseInt(bitString.substring(j,j+7), 2);
				
			if(currentInteger < 33 || currentInteger > 126)
			{
				currentChar = ' ';
			}
			else
			{
				currentChar = (char)currentInteger;
			}		
			decodedCharacters.append(currentChar);
		}
	    return decodedCharacters;
	}
}

