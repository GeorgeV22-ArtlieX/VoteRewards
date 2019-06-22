package com.GeorgeV22.VoteRewards.Utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LastVote {
	public static final int DAY = 1;
	public static final int WEEK = 2;
	public static final int MONTH = 3;

	public final int compare(Date current, Date lastVote) {
		if (lastVote.before(current)) {
			return 0;
		} else if (lastVote.getYear() > current.getYear()) {
			return 3;
		} else if (lastVote.getMonth() > current.getMonth()) {
			return 3;
		} else if (lastVote.getWeek() > current.getWeek()) {
			return 2;
		} else {
			return lastVote.getDay() > current.getDay() ? 1 : 0;
		}
	}

	public static class Date implements Cloneable {
		private final Calendar calendar;

		public Date() {
			this.calendar = Calendar.getInstance();
			this.setMillis(0);
		}

		public Date(long millis) {
			this();
			this.calendar.setTimeInMillis(millis);
		}

		public Date(Calendar calendar) {
			this.calendar = (Calendar) calendar.clone();
		}

		public Date(Date date) {
			this(date.calendar);
		}

		public Date(java.util.Date date) {
			this();
			this.calendar.setTime(date);
		}

		public Date(int year, int month, int day) {
			this();
			this.calendar.set(year, month - 1, day);
		}

		public Date(int year, int month, int day, int hour, int minute) {
			this();
			this.calendar.set(year, month - 1, day, hour, minute);
		}

		public Date(int year, int month, int day, int hour, int minute, int second) {
			this();
			this.calendar.set(year, month - 1, day, hour, minute, second);
		}

		public Date(String raw) throws IllegalArgumentException {
			this();
			if (!raw.matches("\\d{14}")) {
				throw new IllegalArgumentException("Raw date must have the format yyyyMMddHHmmss");
			} else {
				int year = Integer.parseInt(raw.substring(0, 4));
				int month = Integer.parseInt(raw.substring(4, 6));
				int day = Integer.parseInt(raw.substring(6, 8));
				int hour = Integer.parseInt(raw.substring(8, 10));
				int minute = Integer.parseInt(raw.substring(10, 12));
				int second = Integer.parseInt(raw.substring(12, 14));
				this.set(year, month, day, hour, minute, second);
			}
		}

		public int getYear() {
			return this.calendar.get(1);
		}

		public int getMonth() {
			return this.calendar.get(2) + 1;
		}

		public int getWeek() {
			return this.calendar.get(3);
		}

		public int getDay() {
			return this.calendar.get(5);
		}

		public int getHour() {
			return this.calendar.get(11);
		}

		public int getMinute() {
			return this.calendar.get(12);
		}

		public int getSecond() {
			return this.calendar.get(13);
		}

		public int getMillis() {
			return this.calendar.get(14);
		}

		public long toMillis() {
			return this.calendar.getTimeInMillis();
		}

		public void setYear(int year) {
			this.calendar.set(1, year);
		}

		public void setMonth(int month) {
			this.calendar.set(2, month - 1);
		}

		public void setDay(int day) {
			this.calendar.set(5, day);
		}

		public void setHour(int hour) {
			this.calendar.set(11, hour);
		}

		public void setMinute(int minute) {
			this.calendar.set(12, minute);
		}

		public void setSecond(int second) {
			this.calendar.set(13, second);
		}

		public void setMillis(int millis) {
			this.calendar.set(14, millis);
		}

		public void set(int year, int month, int day) {
			this.calendar.set(year, month - 1, day);
		}

		public void set(int year, int month, int day, int hour, int minute) {
			this.calendar.set(year, month - 1, day, hour, minute);
		}

		public void set(int year, int month, int day, int hour, int minute, int second) {
			this.calendar.set(year, month - 1, day, hour, minute, second);
		}

		public Date add(String add) throws IllegalArgumentException {
			if (!add.toLowerCase().matches("\\d+(y|d|h|m|s)")) {
				throw new IllegalArgumentException("Invalid add format");
			} else {
				int amount = Integer.parseInt(add.substring(0, add.length() - 1));
				switch (add.toLowerCase().charAt(add.length() - 1)) {
				case 'd':
					this.calendar.add(5, amount);
					break;
				case 'h':
					this.calendar.add(11, amount);
					break;
				case 'm':
					this.calendar.add(12, amount);
					break;
				case 's':
					this.calendar.add(13, amount);
					break;
				case 'y':
					this.calendar.add(1, amount);
				}

				return this;
			}
		}

		public String format(String format) {
			return this.format((DateFormat) (new SimpleDateFormat(format)));
		}

		public String format(DateFormat format) {
			return format.format(this.calendar.getTime());
		}

		public String rawString() {
			return this.format("yyyyMMddHHmmss");
		}

		public long raw() {
			return Long.parseLong(this.rawString());
		}

		public boolean before(Date date) {
			return this.compareTo(date) < 0;
		}

		public boolean after(Date date) {
			return this.compareTo(date) > 0;
		}

		public String toString() {
			return this.format("yyyy-MM-dd HH:mm:ss");
		}

		public int hashCode() {
			return this.calendar.hashCode();
		}

		public int compareTo(Date date) {
			return date == this ? 0 : this.calendar.compareTo(date.calendar);
		}

		public boolean equals(Object date) {
			try {
				return this.compareTo((Date) date) == 0;
			} catch (Throwable var3) {
				return false;
			}
		}

		public Date clone() {
			return new Date(this.calendar);
		}
	}
}
