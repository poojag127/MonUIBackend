package com.cavisson.monitor.dto;

import java.util.HashMap;
import java.util.List;

public class TreeTableDTO
{
  private HashMap<String, Object> data;
  
//  private List<HashMap<String,String>> children;
  private List<ChildNodeDataDTO> children;
  
  private List<ComponentDTO> components;
  
  private boolean leaf;

  /**
   * @return the leaf
   */
  public boolean isLeaf()
  {
    return leaf;
  }
  /**
   * @return the components
   */
  public List<ComponentDTO> getComponents()
  {
    return components;
  }
  /**
   * @param components the components to set
   */
  public void setComponents(List<ComponentDTO> components)
  {
    this.components = components;
  }
  /**
   * @param leaf the leaf to set
   */
  public void setLeaf(boolean leaf)
  {
    this.leaf = leaf;
  }
  public HashMap<String, Object> getData()
  {
    return data;
  }
  public void setData(HashMap<String, Object> data)
  {
    this.data = data;
  }
  
  
  /**
   * @return the children
   */
  public List<ChildNodeDataDTO> getChildren()
  {
    return children;
  }
  /**
   * @param children the children to set
   */
  public void setChildren(List<ChildNodeDataDTO> children)
  {
    this.children = children;
  }
  @Override
  public String toString()
  {
    return "TreeTableDTO [data=" + data + ", children=" + children + "]";
  }
  

}


