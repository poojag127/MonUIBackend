package com.cavisson.monitor.main;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.cavisson.monitor.constants.MonConstants;
import com.cavisson.monitor.dto.ComponentDTO;
import com.cavisson.monitor.dto.DropDownDTO;
import com.cavisson.monitor.dto.ValidationDTO;

public class MonitorComponent
{
  private int count = 0;

  public List<ComponentDTO> getComponentList(String drivenFileName, String userName)
  {
    ArrayList<ComponentDTO> compDTOList = new ArrayList<ComponentDTO>();

    try
    {
      String menuDrivenFile = MonConstants.DATA_DRIVEN_FILE_PATH + drivenFileName;
      
      File confMonFile = new File(menuDrivenFile);
      
      if (confMonFile.exists())
      {
        JSONParser parser = new JSONParser();

        // taking data from json file
        JSONObject drivenObj = (JSONObject) parser.parse(new FileReader(menuDrivenFile));
        JSONArray compObj = (JSONArray) drivenObj.get(MonConstants.COMPONENTS);
        compDTOList = createListForComponent(compObj);
      }
    } catch (Exception e)
    {

    }
    return compDTOList;
  }

  private ArrayList<ComponentDTO> createListForComponent(JSONArray compList)
  {
    ArrayList<ComponentDTO> compDTOList = new ArrayList<ComponentDTO>();
    for (Object each : compList)
    {
      JSONObject jsonObj = (JSONObject) each;
      ComponentDTO itemComp = createComponentDTO(jsonObj);
      if (itemComp == null)
        continue;
      count = (byte) (count + 1);
      compDTOList.add(itemComp);
    }
    return compDTOList;
  }

  public ComponentDTO createComponentDTO(JSONObject compObj)
  {
    ComponentDTO compDTO = new ComponentDTO();

    compDTO.setId(count);

    if (compObj.get(MonConstants.TYPE) != null)
      compDTO.setType((String) compObj.get(MonConstants.TYPE));
    else
      return null;

    if (compObj.get(MonConstants.LABEL) != null)
      compDTO.setLabel((String) compObj.get(MonConstants.LABEL));

    if (compObj.get(MonConstants.VALUE) != null)
      compDTO.setValue((String) compObj.get(MonConstants.VALUE));

    if (compObj.get(MonConstants.ARGUMENT) != null)
      compDTO.setArg((String) compObj.get(MonConstants.ARGUMENT));

    if (compObj.get(MonConstants.DEFAULT_VALUE) != null)
      compDTO.setArg((String) compObj.get(MonConstants.DEFAULT_VALUE));

    if (compObj.get(MonConstants.VALIDATION) != null)
    {
      try
      {
        JSONObject valItemObj = (JSONObject) compObj.get("VALIDATION");
        ValidationDTO valDto = new ValidationDTO();

        if (valItemObj.get(MonConstants.MINIMUM_VAL) != null)
          valDto.setMin((Integer.parseInt(valItemObj.get(MonConstants.MINIMUM_VAL).toString())));

        if (valItemObj.get(MonConstants.MAXIMUM_VAL) != null)
          valDto.setMax((Integer.parseInt(valItemObj.get(MonConstants.MAXIMUM_VAL).toString())));

        if (valItemObj.get(MonConstants.MAX_LENGTH_VAL) != null)
          valDto.setMaxLength((Integer.parseInt(valItemObj.get(MonConstants.MAX_LENGTH_VAL).toString())));

        if (valItemObj.get(MonConstants.INPUT_TYPE_VAL) != null)
          valDto.setInputType(valItemObj.get(MonConstants.INPUT_TYPE_VAL).toString());

        if (valItemObj.get(MonConstants.START_WITH_VAL) != null)
          valDto.setStartWith(valItemObj.get(MonConstants.START_WITH_VAL).toString());

        if (valItemObj.get(MonConstants.PATTERN_VAL) != null)
          valDto.setPattern(valItemObj.get(MonConstants.PATTERN_VAL).toString());
      } catch (Exception e)
      {
        // TODO: handle exception
      }
    }

    /*** list will be there for dropdown ****/
    if (compObj.get(MonConstants.LIST_COMP) != null)
    {
      ArrayList<DropDownDTO> dropDownListDTO = new ArrayList<DropDownDTO>();
      JSONArray listItem = (JSONArray) compObj.get(MonConstants.LIST_COMP);
      for (Object each : listItem)
      {
        DropDownDTO dropDownDTOObj = new DropDownDTO();
        JSONObject jsonObject = (JSONObject) each;

        if (jsonObject.get(MonConstants.LABEL) != null)
          dropDownDTOObj.setLabel((String) jsonObject.get(MonConstants.LABEL));

        if (jsonObject.get(MonConstants.VALUE) != null)
          dropDownDTOObj.setValue((String) jsonObject.get(MonConstants.VALUE));

        dropDownListDTO.add(dropDownDTOObj);
      }
      compDTO.setDropDownList(dropDownListDTO);
    }
    if (compObj.get(MonConstants.DEPENDENT_COMP) != null)
    {
      ArrayList<ComponentDTO> dependentCompList = new ArrayList<ComponentDTO>();
      JSONArray dependentCompArr = (JSONArray) compObj.get(MonConstants.DEPENDENT_COMP);
      dependentCompList = createListForComponent(dependentCompArr);
      compDTO.setDependentComp(dependentCompList);
    }

    /***** for table component **********/
    if (compObj.get(MonConstants.TABLE_COLUMN_DATA) != null)
    {
      JSONArray jsonColDataList = (JSONArray) compObj.get(MonConstants.TABLE_COLUMN_DATA);
      ArrayList<ComponentDTO> colDataList = createListForComponent(jsonColDataList);
      compDTO.setColumnData(colDataList);
    }

    /*** special case for radio buttons *********/
    if (compObj.get(MonConstants.RADIO_ITEM) != null)
    {
      JSONArray jsonArrItemList = (JSONArray) compObj.get(MonConstants.RADIO_ITEM);
      ArrayList<ComponentDTO> itemList = createListForComponent(jsonArrItemList);
      compDTO.setItems(itemList);
    }
    return compDTO;
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    // TODO Auto-generated method stub

  }

}
