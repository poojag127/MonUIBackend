package com.cavisson.monitor.dto;

import java.util.List;

public class MonitorsDTO
{
	//getting from standard_monitor file - monitor name, description, drivenJsonName, gdfName 
	private String monName ;
	private String description;
	private String drivenJsonName = null;
	private String gdfName = null;
	
	private List<ComponentDTO> compDTOList = null;
	
	public String getMonName()
	{
		return monName;
	}
	
	public void setMonName(String monName)
	{
		this.monName = monName;
	}
	
	public String getGdfName()
	{
		return gdfName;
	}
	
	public void setGdfName(String gdfName)
	{
		this.gdfName = gdfName;
	}
	
	public String getDrivenJsonName()
	{
		return drivenJsonName;
	}
	
	public void setDrivenJsonName(String drivenJsonName)
	{
		this.drivenJsonName = drivenJsonName;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	/**
	 * @return the compDTOList
	 */
	public List<ComponentDTO> getCompDTOList()
	{
		return compDTOList;
	}
	
	/**
	 * @param compDTOList the compDTOList to set
	 */
	public void setCompDTOList(List<ComponentDTO> compDTOList)
	{
		this.compDTOList = compDTOList;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		MonitorsDTO other = (MonitorsDTO) obj;
		if (!monName.equals(other.monName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MonitorsDTO [monName=" + monName + ", description="
				+ description + ", drivenJsonName=" + drivenJsonName
				+ ", gdfName=" + gdfName + ", compDTOList=" + compDTOList + "]";
	}
	
	
}
