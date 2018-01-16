package com.cavisson.monitor.main;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pac1.Bean.Config;
import pac1.Bean.Log;

import com.cavisson.monitor.constants.MonConstants;
import com.cavisson.monitor.dto.ProfileInfoDto;
import com.cavisson.topology.bean.TopologyDTO;

public class MonitorProfileUtility 
{
	private String ClassName = "MonitorProfileUtility";
	
	public List<String> getListOfTopology()
	{
		TopologyDTO topoListDto = new TopologyDTO();
		topoListDto.setOperationName("LIST_OF_TOPOLOGY");
		topoListDto.getTopologyObject();
		
		// for getting name list of available topology
		ArrayList topoList = topoListDto.getTopologyNameList();
		if(topoList != null)
			return topoList;
		else
			return new ArrayList();
	}
	
	public List<ProfileInfoDto> getProfileListFromTopo(String topoName)
	{
		List<ProfileInfoDto> dtoList = new ArrayList<ProfileInfoDto>();
		try
		{
			//Path of the topology
			String dirPath = Config.getWorkPath() + "/mprof/" + topoName;
			
			//filter the .json file extension
			File dir = new File(dirPath);
			File[] files = dir.listFiles(new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.toLowerCase().endsWith(".json");
				}
			});
			
			ProfileInfoDto jsonDataObj = null;
			
			if(files == null)
				return dtoList;
			
			//time format for last modification time
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			for(File eachFile:files)
			{
				jsonDataObj = new ProfileInfoDto();
				String profName = eachFile.getName();
				jsonDataObj.setProfileName(profName.substring(0, profName.lastIndexOf(".")));
				jsonDataObj.setLastUpdatedOn(sdf.format(eachFile.lastModified()));
				JSONParser parser = new JSONParser();
				try 
				{
					//reading profile to read desc, created by and last modified by
					JSONObject jObj = (JSONObject) parser.parse(new FileReader(dirPath + "/" + profName));
					if(jObj.get(MonConstants.PROF_DESC) != null)
						jsonDataObj.setDesc((String)jObj.get(MonConstants.PROF_DESC));
					if(jObj.get(MonConstants.PROF_CREATEDBY) != null)
						jsonDataObj.setCreatedBy((String)jObj.get(MonConstants.PROF_CREATEDBY));	
					if(jObj.get(MonConstants.PROF_LAST_MOD_BY) != null)
						jsonDataObj.setLastUpdatedBy((String)jObj.get(MonConstants.PROF_LAST_MOD_BY));		
				} 
				catch (Exception e) 
				{
					Log.errorLog("MonitorWebService", "getProfileListInTopo", "", "", "Error in reading " + topoName + "/" + profName);
				} 
				
				System.out.println("Profile Info " + jsonDataObj);
			}
			dtoList.add(jsonDataObj);
		}
		catch (Exception e)
		{
			Log.stackTraceLog("MonitorWebService", "getProfileListInTopo", "", "", "Exception = ", e);
		}
		return dtoList;
	}
	
	public static void main(String[] args) 
	{
		
	}
	
}
