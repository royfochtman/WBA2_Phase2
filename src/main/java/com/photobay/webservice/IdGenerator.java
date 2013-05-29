package main.java.com.photobay.webservice;

import java.io.File;

/*
 * This class generates IDs for the ressources (Photographer, PressAgencies, PhotoSells and Jobs)
 */
public class IdGenerator {

	public static int generateID(String path)
	{
		File file = new File("./host/" + path);
		String[] files = file.list();
		int id = 0;
		for(int i=0; i<files.length; i++)
		{
			try
			{
				int fileID = Integer.parseInt(files[i]);
				if(fileID > id)
					id = fileID;			
			}
			catch(NumberFormatException ex)
			{}
		}
		return id + 1;
	}
	
}
