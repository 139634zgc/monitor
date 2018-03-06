package com.bitauto.bdc.modules.login.model;

public class LoginConfig
{
    private String[] sources;
    private String[] authorities;
    public String[] getSources()
    {
        return sources;
    }
    public void setSources(String[] sources)
    {
        this.sources = sources;
    }
    public String[] getAuthorities()
    {
        return authorities;
    }
    public void setAuthorities(String[] authorities)
    {
        this.authorities = authorities;
    }
}
