
package main.java.com.photobay.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;;

/**
 * This Class manipulates an image, encoding it in Base64, and also decoding it.
 * 
 * @author Roy Fochtman, David Wachs
 *
 */
public class ImageManipulation {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		File file = new File("/Users/jeeva/Pictures/wallpapers/water-drop.jpg");
//		
//		try {
//			/*
//			 * Reading a Image file from file system
//			 */
//			FileInputStream imageInFile = new FileInputStream(file);
//			byte imageData[] = new byte[(int)file.length()];
//			imageInFile.read(imageData);
//			
//			/*
//			 * Converting Image byte array into Base64 String 
//			 */
//			String imageDataString = encodeImage(imageData);
//			
//			/*
//			 * Converting a Base64 String into Image byte array 
//			 */
//			byte[] imageByteArray = decodeImage(imageDataString);
//			
//			/*
//			 * Write a image byte array into file system  
//			 */
//			FileOutputStream imageOutFile = new FileOutputStream("/Users/jeeva/Pictures/wallpapers/water-drop-after-convert.jpg");
//			imageOutFile.write(imageByteArray);
//			
//			imageInFile.close();
//			imageOutFile.close();
//			
//			System.out.println("Image Successfully Manipulated!");
//		} catch (FileNotFoundException e) {
//			System.out.println("Image not found" + e);
//		} catch (IOException ioe) {
//			System.out.println("Exception while reading the Image " + ioe);
//		}
//
//	}
	
	/**
	 * Encodes the byte array into base64 string
	 * @param imageByteArray - byte array
	 * @return String a {@link java.lang.String}
	 */
//	public static String encodeImage(byte[] imageByteArray){		
//		return Base64.encodeBase64URLSafeString(imageByteArray);		
//	}
	
	public static String encodeImage(File file) {
		
		try {
			/*
			 * Reading a Image file from file system
			 */
			FileInputStream imageInFile = new FileInputStream(file);
			byte imageData[] = new byte[(int)file.length()];
			imageInFile.read(imageData);
			imageInFile.close();
			return Base64.encodeBase64URLSafeString(imageData);
			
			
			
		}
		catch (FileNotFoundException e) {}
		catch (IOException ioe) {}
		return null;
		
	}
	
	/**
	 * Decodes the base64 string into byte array
	 * @param imageDataString - a {@link java.lang.String} 
	 * @return byte array
	 */
	public static byte[] decodeImage(String imageDataString) {		
		return Base64.decodeBase64(imageDataString);
	}
	
	public static File toFile(byte[] bytes)
	{
		try
		{
			InputStream in = new ByteArrayInputStream(bytes);
			BufferedImage bImageFromConvert = ImageIO.read(in);
			File file = new File("./photoSell.jpg");
			ImageIO.write(bImageFromConvert, "jpg", file);
			return file;
		}
		catch(Exception ex) { return null; } 
	}
}
