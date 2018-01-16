package com.cavisson.monitor.dto;

import java.util.ArrayList;


public class ComponentDTO
{
	private int id = -1;
	private String type;
	private String label = "";
	private String arg = "";
	private String defVal = "";
	private String value = "";
	private ValidationDTO validationObj = null;
	private ArrayList<DropDownDTO> dropDownList; //used for dropdowncomponent
	private ArrayList<ComponentDTO> items; //item for radio button
	private ArrayList<ComponentDTO> dependentComp;
	private ArrayList<ComponentDTO> columnData;
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id) 
	{
		this.id = id;
	}
	
	/**
	 * @return the columnData
	 */
	public ArrayList<ComponentDTO> getColumnData()
	{
		return columnData;
	}
	/**
	 * @param columnData the columnData to set
	 */
	public void setColumnData(ArrayList<ComponentDTO> columnData)
	{
		this.columnData = columnData;
	}
	/**
	 * @return the dependentComp
	 */
	public ArrayList<ComponentDTO> getDependentComp()
	{
		return dependentComp;
	}
	/**
	 * @param dependentComp the dependentComp to set
	 */
	public void setDependentComp(ArrayList<ComponentDTO> dependentComp)
	{
		this.dependentComp = dependentComp;
	}
	/**
	 * @return the items
	 */
	public ArrayList<ComponentDTO> getItems()
	{
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(ArrayList<ComponentDTO> items)
	{
		this.items = items;
	}
	
	/**
	 * @return the dropDownList
	 */
	public ArrayList<DropDownDTO> getDropDownList()
	{
		return dropDownList;
	}
	/**
	 * @param dropDownList the dropDownList to set
	 */
	public void setDropDownList(ArrayList<DropDownDTO> dropDownList)
	{
		this.dropDownList = dropDownList;
	}
	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
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
	/**
	 * @return the arg
	 */
	public String getArg()
	{
		return arg;
	}
	/**
	 * @param arg the arg to set
	 */
	public void setArg(String arg)
	{
		this.arg = arg;
	}
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
	
	public String getDefVal() 
	{
		return defVal;
	}
	
	public void setDefVal(String defVal) 
	{
		this.defVal = defVal;
	}
	
	public ValidationDTO getValidationObj() 
	{
		return validationObj;
	}
	
	public void setValidationObj(ValidationDTO validationObj) 
	{
		this.validationObj = validationObj;
	}

	@Override
	public String toString() {
		return "ComponentDTO [id=" + id + ", type=" + type + ", label=" + label
				+ ", arg=" + arg + ", defVal=" + defVal + ", value=" + value
				+ ", validationObj=" + validationObj + ", dropDownList="
				+ dropDownList + ", items=" + items + ", dependentComp="
				+ dependentComp + ", columnData=" + columnData + "]";
	}
	
	
	
}
