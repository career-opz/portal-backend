package dev.careeropz.portal.backend.cvmanager.controller;

public class CvManagetUrlConstants {
    public static final String USER_PROFILE = "/userprofile";
    public static final String PARAM_USER_ID = "/{userid}";
    public static final String CV_MANAGER_API = "/cv/api/v1";
    public static final String DEFAULT_DOCS = "/default-docs";
    public static final String ACTIVATE = "/activate";
    public static final String DEACTIVATE = "/deactivate";
    public static final String CV_MANAGER_PROFILE = CV_MANAGER_API + USER_PROFILE;
    public static final String CV_MANAGER_PROFILE_BY_ID = CV_MANAGER_PROFILE + PARAM_USER_ID;
    public static final String CV_MANAGER_PROFILE_BY_ID_DEFAULT_DOCS = CV_MANAGER_PROFILE_BY_ID + DEFAULT_DOCS;
    public static final String CV_MANAGER_PROFILE_BY_ID_ACTIVATE = CV_MANAGER_PROFILE_BY_ID + ACTIVATE;
    public static final String CV_MANAGER_PROFILE_BY_ID_DEACTIVATE = CV_MANAGER_PROFILE_BY_ID + DEACTIVATE;

}
