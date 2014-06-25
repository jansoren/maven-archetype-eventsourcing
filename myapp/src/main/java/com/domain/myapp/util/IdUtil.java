package com.domain.myapp.util;

import java.util.UUID;

public class IdUtil {
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}
}
