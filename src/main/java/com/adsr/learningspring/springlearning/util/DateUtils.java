package com.adsr.learningspring.springlearning.util;

import org.springframework.stereotype.*;
import org.springframework.util.*;

import java.util.*;
import java.text.*;

@Component
public class DateUtils {
	public Date createDateFromDateString(String dateString) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();

		if(StringUtils.hasText(dateString)) {
			try {
				date = format.parse(dateString);
			} catch (ParseException e) {

			}
		}

		return date;
	}
}
