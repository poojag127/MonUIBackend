package com.cavisson.monitor.dto;

public class ValidationDTO 
{
    private String inputType = "string";
    private String startWith = "string";
    private String pattern = "^[a-zA-Z][a-zA-Z0-9_-]{0,31}$";
    private int maxLength = -1;
    private int min = -1;
    private int max = -1;

    
    public String getInputType() {
		return inputType;
	}


	public void setInputType(String inputType) {
		this.inputType = inputType;
	}


	public String getStartWith() {
		return startWith;
	}


	public void setStartWith(String startWith) {
		this.startWith = startWith;
	}


	public String getPattern() {
		return pattern;
	}


	public void setPattern(String pattern) {
		this.pattern = pattern;
	}


	public int getMaxLength() {
		return maxLength;
	}


	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}


	public int getMin() {
		return min;
	}


	public void setMin(int min) {
		this.min = min;
	}


	public int getMax() {
		return max;
	}


	public void setMax(int max) {
		this.max = max;
	}


	@Override
	public String toString() {
		return "ValidationDTO [inputType=" + inputType + ", startWith="
				+ startWith + ", pattern=" + pattern + ", maxLength="
				+ maxLength + ", min=" + min + ", max=" + max + "]";
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
