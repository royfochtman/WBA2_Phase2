package main.java.com.photobay.gui;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ClientMain {

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		try {
//            // set system L&F
//            UIManager.setLookAndFeel(UIManager
//                    .getSystemLookAndFeelClassName());
//        } catch (UnsupportedLookAndFeelException e) {
//        } catch (ClassNotFoundException e) {
//        } catch (InstantiationException e) {
//        } catch (IllegalAccessException e) {
//        }
//
//        new LoginFrame();
//    }
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

