package br.com.fourcamp.fourstore.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
	public static boolean validateCPF(String cpf) {
		boolean return1 = true;
		String regex = "\\b([0-9]{3})\\.([0-9]{3})\\.([0-9]{3})\\-([0-9]{2})";

		Pattern padrao = Pattern.compile(regex);

		Matcher match = padrao.matcher(cpf);
		
		return1 = match.find();
		return return1;
	}
	public boolean validateCard(String cardNumbers) {
		
		boolean return1 = true;
		String regex = "\\b([0-9]{4})\\.([0-9]{4})\\.([0-9]{4})\\.([0-9]{4})";	
		Pattern padrao = Pattern.compile(regex);

		Matcher match = padrao.matcher(cardNumbers);
		
		return1 = match.find();
		return return1;
	}
	public boolean validateCelphone(String number) {
		boolean return1 = true;
		String regex = "(\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})";
				
		Pattern padrao = Pattern.compile(regex);
		
		Matcher match = padrao.matcher(number);
		
		return1 = match.find();
		return return1;
		
	}
	
	}
