#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.util;

import java.util.UUID;

public class IdUtil {
	public static String createUUID() {
		return UUID.randomUUID().toString();
	}
}
