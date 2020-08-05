package toby.jwt.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class LocalDateTimeUtil {

	private LocalDateTimeUtil() {
	}

	public static Date asDate(LocalDateTime localDatetime) {

		return Date.from(localDatetime.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static Date asDate(LocalDate localDate) {

		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime asLocalDateTime(Date date) {

		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static LocalDate asLocalDate(Date date) {

		return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
