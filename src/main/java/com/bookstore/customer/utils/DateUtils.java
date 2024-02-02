package com.bookstore.customer.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateUtils {

	private DateUtils() {
	}

	public static LocalDateTime atStartOfDay(LocalDate localDate) {
		return LocalDateTime.of(localDate, LocalTime.MIN);
	}

	public static LocalDateTime atEndOfDay(LocalDate localDate) {
		return LocalDateTime.of(localDate, LocalTime.MAX);
	}
	
	public static String convertLocalDateTimeToString(LocalDateTime localDateTime) {
		// Create DateTimeFormatter instance with specified format
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// Format LocalDateTime to String
		String formattedDateTime = localDateTime.format(dateTimeFormatter);
		return formattedDateTime;
	}
	
	public static String convertLocalDateToString(LocalDate localDateTime) {
		// Create DateTimeFormatter instance with specified format
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		// Format LocalDateTime to String
		String formattedDateTime = localDateTime.format(dateTimeFormatter);
		return formattedDateTime;
	}
	
	public static String convertDateToYYYYDDMM(LocalDateTime localDateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyddMM");
        return localDateTime.format(dateTimeFormatter);
	}
	
	public static String dateFormateForinvoice(LocalDateTime localDateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
        return localDateTime.format(dateTimeFormatter);
	}
	
	public static String convertDateTOMMMMDDYYYY(LocalDateTime localDateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
        return localDateTime.format(dateTimeFormatter);
	}
	
	public static LocalDate localDateTimeToLocalDate(LocalDateTime localDateTime) {
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
		 String date = localDateTime.format(formatter);
		 return LocalDate.parse(date, formatter);
	}
	
	public static LocalDateTime stringToLocalDateTime(LocalDateTime localDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd-yyyy");
		String date = localDateTime.format(formatter);
		return LocalDateTime.parse(date, formatter);
	}
	
	public static LocalDateTime convertStringToLocalDateTime(String generationDate) {
        // Create DateTimeFormatter instance with specified format
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        // Convert String to LocalDateTime using Parse() method
        return LocalDateTime.parse(generationDate,dateTimeFormatter);
	}
	
	public static LocalDateTime epochSecondToLocalDateTime(long epochSecond) {
		return Instant.ofEpochSecond(epochSecond).atZone(ZoneOffset.UTC).toLocalDateTime();
	}

	public static LocalDateTime nowLocalDateTime() {
		return LocalDateTime.now();
	}
}
