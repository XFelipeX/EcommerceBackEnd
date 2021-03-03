package com.apilibrary.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessMessage {
	private final boolean ERROR = false;
	private Object object;
}
