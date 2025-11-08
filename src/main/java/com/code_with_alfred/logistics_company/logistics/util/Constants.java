package com.code_with_alfred.logistics_company.logistics.util;

public class Constants {

    private Constants() {
        // Utility class
    }

    public static final String API_BASE_PATH = "/api/v1";
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;

    // Cache names
    public static final String CACHE_SHIPMENTS = "shipments";
    public static final String CACHE_USERS = "users";
    public static final String CACHE_DASHBOARD = "dashboard";

    // Security constants
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";

    // Date formats
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
}
