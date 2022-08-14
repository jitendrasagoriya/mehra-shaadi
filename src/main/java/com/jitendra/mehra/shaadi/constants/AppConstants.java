package com.jitendra.mehra.shaadi.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    public final static String PIPE = "|";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final CharSequence CANDIDATE_URL_PATH= "/candidate/"; 
    public static final CharSequence SEARCH_URL_PATH= "/search/"; 
    public static final String BATCH_MONITORED = "BatchMonitored";
    public static final String NEXT_DAY_STEP = "nextDayStep";
    public static final String START_BY_KEY = "start_by";
    public static final String START_BY_VALUE_AUTORUN = "AUTORUN";
    public static final String ATTENDANCE_JOB_NAME = "ATTENDANCE";
    public static final String ADHOC_JOB_NAME = "ADHOCATTENDANCE";
    public static final String CLEANUP_JOB_NAME = "CLEANUP";
    public static final String JOB_NAME = "JOB-NAME";
    public static final String JOB_DATE = "JOB-DATE";
    public static final List<CharSequence> BLOCKED_ENDPOINT = Arrays.asList("/candidate/profile/","/search/");
    public static final int DEFAULT_PAGE_NUMBER = 0;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String FEMALE = "Female";
    public static final String MALE = "Male";
}
