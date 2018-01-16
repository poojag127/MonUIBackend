/**
 * Name: Monitor Web Services
 * This class is called by monitor UI as monitor web services. Any request come from the client to it will manage the request.
 */

package com.cavisson.monitor.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;


import com.cavisson.monitor.dto.*;
import com.cavisson.monitor.main.MonitorComponent;
import com.cavisson.monitor.main.MonitorConfigurationUtility;
import com.cavisson.monitor.main.MonitorProfileUtility;
import com.cavisson.topology.bean.TopologyDTO;
import com.cavisson.productui.summary.annotation.Compress;


 @Path("/MonitorWebService")
 public class MonitorWebService 
 {
	 private String ClassName = "MonitorWebService";
	 @Context
	 private UriInfo info;
	 
	 @Context
	 private Request request;
	 
	 private int debugLevel = 0;
	 
	 /*
	  * * This service use for get layout json of productui.
	  * */
	 
	 @GET
	 @Path("/getListOfTopology")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<String> getListOfTopology()
	 {
		 MonitorProfileUtility monProfUtility = new MonitorProfileUtility(); 
		 return monProfUtility.getListOfTopology(); 
	 }
	 
	 @GET
	 @Path("/getProfileListFromTopo/{topoName}")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<ProfileInfoDto> getProfileListFromTopo(@PathParam("topoName") String topoName)
	 {
		 MonitorProfileUtility monProfUtility = new MonitorProfileUtility();
		 return monProfUtility.getProfileListFromTopo(topoName);
	 }
	 
	 
	 @GET
	 @Path("/getTreeTableData")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Compress
	 public TierMonitorDataDTO getTreeTableData(@QueryParam("topoName") String topoName,@QueryParam("jsonName") String jsonName,@QueryParam("userName") String userName)
	 {
	   MonitorConfigurationUtility monConfUtility = new MonitorConfigurationUtility();
	   return monConfUtility.getTreeTable(topoName, userName);
	 }
	 
	 @GET
	 @Path("/getChildNodesDataByCategoryName")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Compress
	 public List<ChildNodeDataDTO> getLeafNodeByCategoryName(@QueryParam("topoName") String topoName,@QueryParam("jsonName") String profileName,@QueryParam("categoryName") String categoryName, @QueryParam("userName") String userName)
	 {
	   MonitorConfigurationUtility monConfUtility = new MonitorConfigurationUtility();
	   return monConfUtility.getLeafNodeByCategoryName(topoName, profileName, categoryName, userName);
	 }
	 
	 
	 @GET
	 @Path("/getServerList")
	 @Produces(MediaType.APPLICATION_JSON)
	 public List<String> getServerListByTierId(@QueryParam("topoName") String topoName,@QueryParam("tierId") Integer tierId)
	 {
		 TopologyDTO topoStructDto = new TopologyDTO();
		 topoStructDto.setTopologyName(topoName);            
		 topoStructDto.setOperationName("TOPOLOGY_STRUCTURE");
		 topoStructDto.getTopologyObject();
		 List<String> serverList  = new ArrayList<String>();
		 serverList = topoStructDto.getServerNamesByTierId(tierId);
		 return serverList;
	 }
	 
	 @GET
	 @Path("/getComponentByMon")
	 @Produces(MediaType.APPLICATION_JSON)
	 @Compress
	 public List<ComponentDTO> getComponentByMon(@QueryParam("menuDrivenJsonName") String menuDrivenJsonName, @QueryParam("userName") String userName)
	 {
	    MonitorComponent monComp = new MonitorComponent();
		return monComp.getComponentList(menuDrivenJsonName, userName);
	 }
	 
	 public static void main(String args[])
	 {
		 MonitorWebService web = new MonitorWebService();
		 //web.getProfileListInTopo("test");
		 //web.getMonInfoListWithUserFilter("cavisson",(byte)1);
		// web.getTreeTableData("test", "prof1", "cavisson");
		 //https://10.10.50.5/ProductUI/productSummary/MonitorWebService/getLeafNodeByCategoryName?topoName=windowTopology&userName=Cavisson&profileName=prof1&categoryName=TCP
		 //System.out.println(web.getLeafNodeByCategoryName("test", "prof1", "TCP", "cavisson"));
		 System.out.println(web.getComponentByMon("abc.json", "cavisson"));
	 }
}
