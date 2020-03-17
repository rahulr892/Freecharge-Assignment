package com.freecharge.constants;

/*############################################################
 * ' Class Name: constants
 * ' Purpose: constants in project along with file paths
 */
public class Constants {

    public final static String Win_Chrome = "Win_Chrome";
    public final static String Win_IE = "Win_IE";
    public final static String Win_FF = "Win_FF";
    public final static String Mac_Chrome = "Mac_Chrome";
    public final static String Mac_Safari = "Mac_Safari";

    private final static String rootDir = System.getProperty("user.dir");

    public final static String TESTDATA_LOC = rootDir + "/src/main/java/com/freecharge/testdata/TestData.xlsx";

    public final static String CONFIG_LOC = rootDir + "/src/main/java/com/freecharge/testdata/Config.xlsx";

    public final static String FILES_FOR_UPLOAD_LOC = rootDir + "/src/test/java/resources/samplefiles/";

    public final static String ENV_PROPERTIES = rootDir + "/src/main/resources/environment.properties";

    public static final String REPORTS_LOC = rootDir + "/ExtentReports/Reports/";

    public final static String SSH_KEY = rootDir + "/src/main/resources/smk.ppk";

}

