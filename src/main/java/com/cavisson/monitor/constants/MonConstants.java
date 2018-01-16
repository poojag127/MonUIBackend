 package com.cavisson.monitor.constants;

import pac1.Bean.Config;

 public interface MonConstants
 {
	 public static String STANDARD_MONITOR_FILE_PATH = Config.getWorkPath() + "/etc/standard_monitors.dat" ;
	 
	 public static final String PROF_DESC = "Desc" ;
	 public static final String PROF_CREATEDBY = "CreatedBy" ;
	 public static final String PROF_LAST_MOD_BY = "LastModifiedBy" ;
	 
	 //Index of Standard monitor dat file
	 public static final int CATAGORY_INDEX = 10; //Main category Name
	 public static final int GDF_INDEX = 1; //gdf 
	 public static final int DESCRIPTION_INDEX = 8;
	 public static final int SUBCATAGORY_INDEX = 0; //monitor name
	 public static final int INGUI_INDEX = 9; //show in gui or not
	 public static final int JSONNAME_INDEX = 11; //json name for menu drive data gui
	 
	 //Minimum index of standard monitor file
	 public static final int LOWER_LENGTH = 9 ;
	 public static String DATA_DRIVEN_FILE_PATH = Config.getWorkPath() + "/webapps/sys/monDataDriven/";
	 public static String USER_HIDDEN_MON_CONF_PATH = Config.getWorkPath() + "/webapps/sys/mon.netstorm.conf";
	 public static String FILE_PATH = "/etc/MonUIDriven.json" ;
	 
	 
	 public static String CATEGORY = "category";
	 public static String SUBCATEGORY = "subCategory";
	 public static String COMPONENTS = "components";  
	 
	 //HTML component name to driven the Monitor UI
	 public static String TYPE = "type";
	 public static String LABEL = "label";
	 public static String VALUE = "value";
	 public static String DEFAULT_VALUE = "defVal";
	 public static String ARGUMENT = "args";
	 
	 /** "input": "string",
     "min": 6,
     "max": "256",
     "startWith": "string",
     "maxLength": 20,
     "pattern": "^[a-zA-Z][a-zA-Z0-9_-]{0,31}$"**/
	 public static String VALIDATION = "validation";
	 public static String INPUT_TYPE_VAL = "input";
	 public static String MINIMUM_VAL = "min";
	 public static String MAXIMUM_VAL = "max";
	 public static String START_WITH_VAL = "startWith";
	 public static String MAX_LENGTH_VAL = "maxLength";
	 public static String PATTERN_VAL = "pattern";
	 
	 public static String LIST_COMP = "list";
	 public static String RADIO_ITEM = "item";
	 public static String TABLE_COLUMN_DATA = "columnData";
	 public static String DEPENDENT_COMP = "dependentComp";
	 
	/** public static String RADIO_COMP = "radio";
	 public static String TEXT_FIELD_COMP = "textField";
	 public static String CHECKBOX_COMP = "checkBox";
	 public static String TABLE_COMP = "table";
	 public static String COLUMN_DATA = "colData";
	 **/
	 
	 
 }
