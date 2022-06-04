package br.com.fourcamp.fourstore;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import br.com.foursys.fourcamp.fourstore.communication.MainMenu;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		menu.MainMenu();
	}

}
