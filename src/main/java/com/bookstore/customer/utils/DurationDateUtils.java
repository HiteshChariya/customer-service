package com.bookstore.customer.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.springframework.util.StringUtils;

public class DurationDateUtils {

	public static final String WEEK = "week";
	public static final String MONTH = "month";
	public static final String YEAR = "year";

	private final LocalDateTime firstDay;
	private final LocalDateTime lastDay;

	public DurationDateUtils(String duration, LocalDateTime initial) {
		
		if(!StringUtils.hasText(duration))
			duration = MONTH;
		
		switch (duration) {
		case WEEK :
			final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.US).getFirstDayOfWeek();
			final DayOfWeek lastDayOfWeek = DayOfWeek
					.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

			this.firstDay = initial.with(TemporalAdjusters.previousOrSame(firstDayOfWeek)).with(LocalTime.MIN);
			this.lastDay = initial.with(TemporalAdjusters.nextOrSame(lastDayOfWeek)).with(LocalTime.MAX);
			break;
		case MONTH :
			this.firstDay = initial.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
			this.lastDay = initial.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
			break;
		case YEAR :
			this.firstDay = initial.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN);
			this.lastDay = initial.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX);
			break;
		default :
			this.firstDay = initial.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN);
			this.lastDay = initial.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX);
		
		}
	}

	public LocalDateTime getFirstDay() {
		return firstDay;
	}

	public LocalDateTime getLastDay() {
		return lastDay;
	}
}
