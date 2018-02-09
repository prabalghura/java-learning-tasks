package com.jlt.genericenum;

/**
 * An enum class implementing generic enum behaviour.
 * 
 * @author Prabal Ghura
 *
 */
public enum WeekDay implements GenericEnum<String>{
	MONDAY("Monday"), 
	TUESDAY("Tuesday"), 
	WEDNESDAY("Wednesday"), 
	THURSDAY("Thursday"), 
	FRIDAY("Friday"), 
	SATURDAY("Saturday"),
	SUNDAY("Sunday");

	String val;
	
	private WeekDay(String val) {
		this.val = val;
	}

	@Override
	public String getVal() {
		return this.val;
	}
}
