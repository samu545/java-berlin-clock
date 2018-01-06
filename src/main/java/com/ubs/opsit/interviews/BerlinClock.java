package com.ubs.opsit.interviews;

import java.util.stream.Stream;

import org.apache.commons.lang.text.StrBuilder;

/**
 * 
 * @author Samraj
 *
 */
public class BerlinClock implements TimeConverter {

	@Override
	public String convertTime(String aTime){
		return new StrBuilder().appendWithSeparators(
				convertToBerlinTime(aTime), 
				System.lineSeparator()
				).toString();
	}

	public String[] convertToBerlinTime(String time) {
		int[] parts = Stream.of(time.split(":")).mapToInt(Integer::parseInt).toArray();
		return new String[] {
				getSeconds(parts[2]),
				getTopHours(parts[0]),
				getBottomHours(parts[0]),
				getTopMinutes(parts[1]),
				getBottomMinutes(parts[1])
		};
	}

	public String getSeconds(int number) {
		if (number % 2 == 0) return "Y";
		else return "O";
	}

	public String getTopHours(int number) {
		return getOnOff(4, getTopNumberOfOnSigns(number));
	}

	public String getBottomHours(int number) {
		return getOnOff(4, number % 5);
	}

	public String getTopMinutes(int number) {
		return getOnOff(11, getTopNumberOfOnSigns(number), "Y").replaceAll("YYY", "YYR");
	}

	public String getBottomMinutes(int number) {
		return getOnOff(4, number % 5, "Y");
	}

	private String getOnOff(int lamps, int onSigns) {
		return getOnOff(lamps, onSigns, "R");
	}

	private String getOnOff(int lamps, int onSigns, String onSign) {
		String out = "";
		for (int i = 0; i < onSigns; i++) {
			out += onSign;
		}
		for (int i = 0; i < (lamps - onSigns); i++) {
			out += "O";
		}
		return out;
	}

	private int getTopNumberOfOnSigns(int number) {
		return (number - (number % 5)) / 5;
	}

}
