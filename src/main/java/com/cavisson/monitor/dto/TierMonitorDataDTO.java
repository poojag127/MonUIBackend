package com.cavisson.monitor.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TierMonitorDataDTO
{
  private ArrayList<TierDTO> tierList;
  private HashMap<String, List<TreeTableDTO>> treeTableData = new HashMap<String, List<TreeTableDTO>>();
  
  
  /**
   * @return the tierList
   */
  public ArrayList<TierDTO> getTierList()
  {
    return tierList;
  }
  /**
   * @param tierList the tierList to set
   */
  public void setTierList(ArrayList<TierDTO> tierList)
  {
    this.tierList = tierList;
  }
  /**
   * @return the treeTableData
   */
  public HashMap<String, List<TreeTableDTO>> getTreeTableData()
  {
    return treeTableData;
  }
  /**
   * @param treeTableData the treeTableData to set
   */
  public void setTreeTableData(HashMap<String, List<TreeTableDTO>> treeTableData)
  {
    this.treeTableData = treeTableData;
  }
@Override
public String toString() {
	return "TierMonitorDataDTO [tierList=" + tierList + ", treeTableData="
			+ treeTableData + "]";
}

  
}
