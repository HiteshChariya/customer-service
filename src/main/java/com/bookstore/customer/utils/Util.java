package com.bookstore.customer.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort;
import org.springframework.lang.NonNull;
import org.springframework.util.FileSystemUtils;

import com.bookstore.customer.response.PageRequestData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Util {
	
	private static final String DATE_FORMAT_YYYYMMDD = "MMddyyyy";
	
	public static LocalDateTime convertLocalDateToUtc(String docGenerationDate){
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_YYYYMMDD);
		 LocalDate localDate = LocalDate.parse(docGenerationDate.length() == 8 ? docGenerationDate : "0"+docGenerationDate,formatter);
		 Date date = Date.from(localDate.atStartOfDay().toInstant(ZoneOffset.UTC));
		 return date.toInstant().atOffset(ZoneOffset.UTC).toLocalDateTime();
	}
	
	public static boolean deleteDirectory(File file) {
		return FileSystemUtils.deleteRecursively(file);
	}
	
	public static PageRequestData getPageRequestData(Integer pageSize, Integer pageNumber, String sortOrder, String sortParam) {
		pageSize = pageSize != null ? pageSize : 10;
		pageNumber = pageNumber != null ? pageNumber : 0;
		sortOrder = sortOrder != null ? sortOrder : "desc";
		sortParam = sortParam != null ? sortParam : "createdAt";
		return new PageRequestData(pageSize, pageNumber,sortOrder,sortParam);
	}

	public static boolean isDigit(@NonNull String str) {
		 return str.matches("[0-9]+");
	}
	
	public static void deleteTempFile(Path path) {
		try {
			Files.delete(path);
		} catch (java.io.IOException e) {
			log.info("ERROR WHEN DELETE TEMP FILE {} ",e.getMessage());
		}
	}
	
	public static String getOrderDirection(String sortOrder) {
		String order = null;
		switch (sortOrder) {
		case "asc" : order = "asc";
		case "desc" : order = "desc";
		default : order = "desc";
		}
		return order;
	}
	
	public static Sort getSortingOrder(String order, String param) {
		String orderDirection = getOrderDirection(order);
		return orderDirection.equals("asc") ? Sort.by(param).ascending() : Sort.by(param).descending();
	}
	
	public static boolean checkValidMail(String emailAddress) {
		String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
				+ "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		return Pattern.compile(regexPattern).matcher(emailAddress).matches();
	}
	
	public static PageRequestData getPageRequestData(Integer pageSize, Integer pageNumber) {
		pageSize = pageSize != null ? pageSize : 10;
		pageNumber = pageNumber != null ? pageNumber : 0;
		return new PageRequestData(pageSize, pageNumber, null, null);
	}
	
	public static List<Long> convertStringToList(String values) {
		values = values.replace("[", "").replace("]", "");
		return Arrays.stream(values.split(",")).map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
	}

	public static Long convertBytesToMb(long sizeInByte) {
		long convertToMb = 1024L * 1024L;
		long fileSizeInMb = sizeInByte / convertToMb;
		log.info(fileSizeInMb + " Mb");
		return fileSizeInMb;
	}
	
	public static Long convertToKB(long sizeInByte) {
		long convertToMb = 1024L;
		long fileSizeInMb = sizeInByte / convertToMb;
		log.info(fileSizeInMb + " Kb");
		return fileSizeInMb;
	}
	
	public static Long convertToGB(long sizeInByte) {
		long convertToMb = 1024L * 1024L * 1024L;
		long fileSizeInMb = sizeInByte / convertToMb;
		log.info(fileSizeInMb + " Kb");
		return fileSizeInMb;
	}
	
	public static String getSize(Long sizeInByte) {
		String size = String.valueOf(convertBytesToMb(sizeInByte));
		if(size.equals("0"))
			return String.valueOf(convertToKB(sizeInByte))+ " KB";
		
		return size+ " MB";
	}
	
	public static String convertCamelToPascalCase(String column) {
		String[] columnArray = StringUtils.splitByCharacterTypeCamelCase(column);
		return StringUtils.capitalize(Arrays.asList(columnArray).stream().collect(Collectors.joining(" ")));
	}
	
	public static String generateShortCode(String name) {
		StringBuilder builder = new StringBuilder();
        if (name.length() == 0)
            return null;
        String[] codes = name.split("\\s");
        
		if (codes.length == 1) {
			builder.append(codes[0].charAt(0)).append(codes[0].charAt(codes[0].length() - 1));
		}else {
			for (int i = 0; i < codes.length; i++) {
					builder.append(codes[i].charAt(0));
			}
		}
        return builder.toString().toUpperCase().substring(0, 2);
	}
	
	public static String convertToCamelCase(String columnName) {
		int ctr = 0;
		int n = columnName.length();
		char ch[] = columnName.toCharArray();
		int c = 0;
		for (int i = 0; i < n; i++) {
			if (i == 0)
				ch[i] = Character.toLowerCase(ch[i]);
			if (ch[i] == ' ') {
				ctr++;
				ch[i + 1] = Character.toUpperCase(ch[i + 1]);
				continue;
			} else
				ch[c++] = ch[i];
		}
		return String.valueOf(ch, 0, n - ctr);
	}
}
