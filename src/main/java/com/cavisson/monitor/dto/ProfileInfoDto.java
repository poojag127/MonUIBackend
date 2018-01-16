
package com.cavisson.monitor.dto;

public class ProfileInfoDto
{
	private String profileName;
	private String desc = "NA";
	private String lastUpdatedOn;
	private String createdBy = "Cavisson";
	private String lastUpdatedBy = "Cavisson";
	
	public String getProfileName() 
	{
		return profileName;
	}
	
	public void setProfileName(String profileName) 
	{
		this.profileName = profileName;
	}
	
	public String getDesc() 
	{
		return desc;
	}
	
	public void setDesc(String desc) 
	{
		this.desc = desc;
	}
	
	public String getLastUpdatedOn() 
	{
		return lastUpdatedOn;
	}
	
	public void setLastUpdatedOn(String lastUpdatedOn) 
	{
		this.lastUpdatedOn = lastUpdatedOn;
	}
	
	public String getCreatedBy() 
	{
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy) 
	{
		this.createdBy = createdBy;
	}
	
	public String getLastUpdatedBy() 
	{
		return lastUpdatedBy;
	}
	
	public void setLastUpdatedBy(String lastUpdatedBy) 
	{
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public String toString() {
		return "ProfileInfoDto [profileName=" + profileName + ", desc=" + desc
				+ ", lastUpdatedOn=" + lastUpdatedOn + ", createdBy="
				+ createdBy + ", lastUpdatedBy=" + lastUpdatedBy + "]";
	}
	
	
	
}