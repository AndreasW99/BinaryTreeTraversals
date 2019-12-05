import java.io.IOException;

//********************************************************************
//  MalformedTreeFileException.java     Author: CS1027
//
//  Represents the situation in which a target element is not
//  present in a collection
//********************************************************************

public class MalformedTreeFileException extends IOException {
	/******************************************************************
	 * Sets up this exception with an appropriate message.
	 ******************************************************************/
	public MalformedTreeFileException(String filename, String token) {
		super("The tree file is not the correct format: " + filename + "\nError near token: " + token);
	}
}