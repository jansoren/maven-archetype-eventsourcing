#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.monitoring;

import org.joda.time.DateTime;

public class ApplicationStatus {

	private DateTime firstStart;
	private DateTime lastStart;
	private int startCounter;

	public DateTime getFirstStart() {
		return firstStart;
	}
	public void setFirstStart(DateTime firstStart) {
		this.firstStart = firstStart;
	}
	public DateTime getLastStart() {
		return lastStart;
	}
	public void setLastStart(DateTime lastStart) {
		this.lastStart = lastStart;
	}
	public int getStartCounter() {
		return startCounter;
	}
	public void setStartCounter(int startCounter) {
		this.startCounter = startCounter;
	}
}
