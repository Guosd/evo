package com.github.framework.evo.code.generator.domain;

public class Db {
    private String driverName;
    private String user;
    private String schema;
    private String password;
    private String url;

    public Db() {
    }

    public String getDriverName() {
        return this.driverName;
    }

    public String getUser() {
        return this.user;
    }

    public String getSchema() {
        return this.schema;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUrl() {
        return this.url;
    }

    public void setDriverName(final String driverName) {
        this.driverName = driverName;
    }

    public void setUser(final String user) {
        this.user = user;
    }

    public void setSchema(final String schema) {
        this.schema = schema;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setUrl(final String url) {
        this.url = url;
    }


    @Override
    public String toString() {
        return "Db(driverName=" + this.getDriverName() + ", user=" + this.getUser() + ", schema=" + this.getSchema() + ", password=" + this.getPassword() + ", url=" + this.getUrl() + ")";
    }
}