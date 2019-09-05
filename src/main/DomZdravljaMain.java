package main;

import domZdravlja.DomZdravlja;
import gui.LoginProzor;

public class DomZdravljaMain {

	public static void main(String[] args) {
		DomZdravlja domZdravlja = new DomZdravlja();
		LoginProzor prozor = new LoginProzor(domZdravlja);
		prozor.setVisible(true);

	}

}
