package com.abidi.users_microservices.security;

public interface SecParams {
    public static final long EXP_TIME = 10*24*60*60*1000;
    public static final String SECRET = "abidi@yahoo.com";
    public static final String PREFIX = "Bearer ";
}
