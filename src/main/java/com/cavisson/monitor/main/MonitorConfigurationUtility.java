package com.cavisson.monitor.main;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import pac1.Bean.Log;
import pac1.Bean.rptUtilsBean;

import com.cavisson.monitor.constants.MonConstants;
import com.cavisson.monitor.dto.ChildNodeDataDTO;
import com.cavisson.monitor.dto.MonitorsDTO;
import com.cavisson.monitor.dto.TierMonitorDataDTO;
import com.cavisson.monitor.dto.TierDTO;
import com.cavisson.monitor.dto.TreeTableDTO;
import com.cavisson.topology.bean.Tier;
import com.cavisson.topology.bean.TopologyDTO;

public class MonitorConfigurationUtility 
{
	private String ClassName = "MonitorConfigurationUtility";
	/**
	 * @param args
	 */
	
	/***
	 * 
	 * This method return
	 * 1. object for treetable component = treeTableData
	 * 2. list of Tiers  = tierList
	 * 
	 * 
	 * @param topoName
	 * @param jsonName
	 * @return
	 */
	
	public HashMap<String, ArrayList<String>> getUserHiddenMonitor(String userName)
	{
		HashMap<String, ArrayList<String>> monUserMap = new HashMap<String, ArrayList<String>>();
		try
		{
			String userConfFile = MonConstants.USER_HIDDEN_MON_CONF_PATH;
			File confMonFile = new File(userConfFile);
			if(confMonFile.exists())
			{
				JSONParser parser = new JSONParser();
				
				// taking data from json file
				JSONObject arrData = (JSONObject) parser.parse(new FileReader(userConfFile));
				
				JSONArray monObj =  (JSONArray) arrData.get(userName);
				
				for (Object monEach : monObj)
				{
					JSONObject cateObj = (JSONObject) monEach;
					
					String categoryName = (String)cateObj.get(MonConstants.CATEGORY);
					
					ArrayList<String> list = new ArrayList<String>();
					JSONArray subC = (JSONArray)cateObj.get(MonConstants.SUBCATEGORY);
					
					for (Object each : subC)
						list.add((String)each);
					
					monUserMap.put(categoryName, list);
				}
			}
		}
		catch(Exception e)
		{
			Log.stackTraceLog(ClassName, "getUserHiddenMonitor", "", "", "", e);
			e.printStackTrace();
		}
		return monUserMap;
	}	
	
	
	/*
	 * Get create configuration json from standard monitor file
	 * getMode = 0 all come
	 *           1 all come and hide user monitor (remove hidden monitor from object)
	 *           2 all come with mark hidden mark
	 * */
	public HashMap<String, ArrayList<MonitorsDTO>>  getMonInfoListWithUserFilter(String userName, byte getMode)
	{
		Log.debugLogAlways(ClassName, "getMonTierTableData", "", "", "Method called. user Name = " + userName + ", Hide user hiddem monittor or not = " + getMode);
		
		HashMap<String, ArrayList<MonitorsDTO>> filerMonMap = new HashMap<String, ArrayList<MonitorsDTO>>();
		
		try
		{
			String stdMonFilePath = MonConstants.STANDARD_MONITOR_FILE_PATH;
			File stdMonFile = new File(stdMonFilePath);
			if(!stdMonFile.exists())
			{
				Log.errorLog("MonitorWebService", "getMonTierTableData", "", "", "Standard Monitor does not exists = " + stdMonFilePath);
				return filerMonMap;
			}
			
			Vector<String> vecData = rptUtilsBean.readFileInVector(stdMonFilePath);
			for(int i = 0; i < vecData.size(); i++)
			{
				String strLine = vecData.get(i).toString();
				String [] arrData = rptUtilsBean.split(strLine, "|");
				if(arrData.length <= MonConstants.LOWER_LENGTH)
					continue;
				if(arrData[MonConstants.INGUI_INDEX].equalsIgnoreCase("NO"))
					continue;
				
				if(filerMonMap.get(arrData[MonConstants.CATAGORY_INDEX]) == null)
				{
					ArrayList<MonitorsDTO> subCategoryList = new ArrayList<MonitorsDTO>();
					filerMonMap.put(arrData[MonConstants.CATAGORY_INDEX], subCategoryList);
				}
				
				MonitorsDTO subCategoryObj = new MonitorsDTO();
				subCategoryObj.setMonName(arrData[MonConstants.SUBCATAGORY_INDEX]);
				subCategoryObj.setDescription(arrData[MonConstants.DESCRIPTION_INDEX]);
				subCategoryObj.setDrivenJsonName(arrData[MonConstants.JSONNAME_INDEX]);
				
				subCategoryObj.setGdfName(arrData[MonConstants.GDF_INDEX]);
				
				ArrayList<MonitorsDTO> subCategoryList = filerMonMap.get(arrData[MonConstants.CATAGORY_INDEX]);
				subCategoryList.add(subCategoryObj);
			}
			
			if(getMode == 1)
			{
				HashMap<String, ArrayList<String>> monUserMap = getUserHiddenMonitor(userName);
				for(Map.Entry<String, ArrayList<String>> keywordEntry: monUserMap.entrySet())
				{
					String strCategory = keywordEntry.getKey();
					
					ArrayList<String> arrSubC = keywordEntry.getValue();
					if(arrSubC.size() == 0)
					{
						if(filerMonMap.get(strCategory) != null)
						{
							filerMonMap.remove(strCategory);
							continue;
						}
					} 
					
					ArrayList<MonitorsDTO> monListDTO = filerMonMap.get(strCategory);
					for(int i  = 0; i < arrSubC.size(); i++)
					{
						MonitorsDTO temoM = new MonitorsDTO();
						temoM.setMonName(arrSubC.get(i));
						monListDTO.remove(temoM);
					}
					
					if(monListDTO.size() == 0)
						filerMonMap.remove(strCategory);  
				}
			}
			printData(filerMonMap);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
			Log.stackTraceLog("MonitorWebService", "getMonTierTableData", "", "", "", e);
		}
		return filerMonMap;
	}	
	
	private void printData(HashMap<String, ArrayList<MonitorsDTO>> filerMonMap)
	{
		System.out.println("--------------------");
		for(Map.Entry<String, ArrayList<MonitorsDTO>> keywordEntry: filerMonMap.entrySet())
		{
			String strKeyword = keywordEntry.getKey();
			
			ArrayList<MonitorsDTO> perGrpData = keywordEntry.getValue();
			String str = "";
			for(int i  = 0; i < perGrpData.size(); i++)
			{
				str = str + " " + perGrpData.get(i).getMonName() + "//" + perGrpData.get(i).getDescription() + "//" + perGrpData.get(i).getDrivenJsonName() + "\n";
			}
			System.out.println("Category = " + strKeyword );
			System.out.println("Subcategory Name = " + str );
		}
	}	
	
	
	public ArrayList<TierDTO> getTierList(String topoName)
	{
		System.out.println("Method getTierList called for topology" + topoName);
		TopologyDTO topoStructDto = new TopologyDTO();
		topoStructDto.setTopologyName(topoName);
		topoStructDto.setOperationName("TOPOLOGY_STRUCTURE");
		topoStructDto.getTopologyObject();
		
		List<Tier> tierList = topoStructDto.getAllTierObjects();
		ArrayList<TierDTO> tierDTOList = new ArrayList<TierDTO>();
		for (Tier tierObj : tierList)
		{
			TierDTO obj = new TierDTO();
			obj.setId(tierObj.getTierId());
			obj.setName(tierObj.getTierName());
			tierDTOList.add(obj);
		}
		System.out.println("tierDTOList--" + tierDTOList.size());
		return tierDTOList;
	}	
	
	
	public TierMonitorDataDTO getTreeTable(String topoName, String userName)
	{
		ArrayList<TierDTO> tierList = getTierList(topoName);
		
		HashMap<String, ArrayList<MonitorsDTO>> filerMonMap = getMonInfoListWithUserFilter(userName, (byte)1);
		
		List<TreeTableDTO> treeTableDTOList = createTreeTableData(tierList, filerMonMap);
		
		TierMonitorDataDTO tierMonDtoObj = new TierMonitorDataDTO();
		tierMonDtoObj.setTierList(tierList);
		HashMap<String, List<TreeTableDTO>> hm = new HashMap<String,List<TreeTableDTO>>();
		hm.put("data", treeTableDTOList);
		tierMonDtoObj.setTreeTableData(hm);
		
		System.out.println("xxxxxxxxxxxxx " + tierMonDtoObj);
		return tierMonDtoObj;
		
	}
	private void createRowDataForEachNode(HashMap<String, Object> node, ArrayList<TierDTO> tierList)
	{
		for (TierDTO tier : tierList)
		{
			node.put(tier.getName(), false);
		}
	}
	
	public ArrayList<TreeTableDTO> createTreeTableData(ArrayList<TierDTO> tierList, HashMap<String, ArrayList<MonitorsDTO>> filerMonMap)
	{
		ArrayList<TreeTableDTO> treetableDTOList = new ArrayList<TreeTableDTO>();
		TreeTableDTO node = null;
		
		for(Map.Entry<String, ArrayList<MonitorsDTO>> catgegoryEntry: filerMonMap.entrySet())
		{
			String monCategory = catgegoryEntry.getKey();
			System.out.println("monCategory " + monCategory);
			node = new TreeTableDTO();
			HashMap<String, Object> dataNode = new HashMap<String, Object>();
			dataNode.put("monitor", monCategory);
			dataNode.put("monitorState", false);
			createRowDataForEachNode(dataNode, tierList);
			
			if(catgegoryEntry.getValue().size() > 1)
				node.setLeaf(true);
			else
				node.setLeaf(false);
			
			node.setData(dataNode);
			
			List<ChildNodeDataDTO> childMonList = new ArrayList<ChildNodeDataDTO>();
			node.setChildren(childMonList);
			treetableDTOList.add(node);
		}
		
		return treetableDTOList;
	}
	
	
	public List<ChildNodeDataDTO> getLeafNodeByCategoryName(String topoName, String profileName, String categoryName, String userName)
	{
		ArrayList<TierDTO> tierList = getTierList(topoName);
		
		HashMap<String, ArrayList<MonitorsDTO>> filerMonMap = getMonInfoListWithUserFilter(userName, (byte)1);
		
		 List<MonitorsDTO> monitorsDataList = filerMonMap.get(categoryName);
		return createChildNodesList(tierList, monitorsDataList);
	}
	
	
	  public List<ChildNodeDataDTO> createChildNodesList(ArrayList<TierDTO> tierList, List<MonitorsDTO> monitorsDataList)
	  {
		  List<ChildNodeDataDTO> treetableDTOList = new ArrayList<ChildNodeDataDTO>();
		  
		  for (MonitorsDTO monitor : monitorsDataList)
		  {
			  HashMap<String, Object> eachChildNode = new HashMap<String, Object>();
			  eachChildNode.put("monitor", monitor.getMonName());
			  eachChildNode.put("monitorState", false);
			  eachChildNode.put("gdfName", monitor.getGdfName());
			  eachChildNode.put("drivenJsonName", monitor.getDrivenJsonName());
			  eachChildNode.put("description", monitor.getDescription());
			  createRowDataForEachNode(eachChildNode, tierList);
			  ChildNodeDataDTO childNodeDTOObj = new ChildNodeDataDTO();
			  childNodeDTOObj.setData(eachChildNode);
			  treetableDTOList.add(childNodeDTOObj);
		  }
		  return treetableDTOList;
	  }
		

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
}
