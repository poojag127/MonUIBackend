package com.cavisson.monitor.dto;

public class DropDownDTO
{
  private String label;
  private String value;
  
  /**
   * @return the label
   */
  public String getLabel()
  {
    return label;
  }
  /**
   * @param label the label to set
   */
  public void setLabel(String label)
  {
    this.label = label;
  }
  /**
   * @return the value
   */
  public String getValue()
  {
    return value;
  }
  /**
   * @param value the value to set
   */
  public void setValue(String value)
  {
    this.value = value;
  }
@Override
public String toString() {
	return "DropDownDTO [label=" + label + ", value=" + value + "]";
}

  
}
