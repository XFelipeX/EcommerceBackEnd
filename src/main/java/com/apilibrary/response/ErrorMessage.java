package com.apilibrary.response;

import java.util.GregorianCalendar;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorMessage {
	@JsonFormat(pattern = "yyyy-MM-dd")
	private GregorianCalendar currentDate;
	private String localMessage;
	private String message;
	private final boolean ERROR = true;
}
