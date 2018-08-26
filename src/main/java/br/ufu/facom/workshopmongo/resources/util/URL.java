package br.ufu.facom.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class URL {

	public static String decodeParam(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			// Caso de erro, retorna vazio
			return "";
		}
	}
	
	public static Date convertDate(String textDate, Date defaultValue) {
		// Os comandos abaixo formatam a data
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT")); // GMT = Greenwich Mean Time
		// Fim dos comandos 
		
		try {
			return sdf.parse(textDate);
		} 
		catch (ParseException e) {
			// Caso a conversao de errado, devolvo a data num formato padrao
			return defaultValue;
		}	
	}
}
