package com.github.framework.evo.code.generator.schema;


public class Database {
    private String productName;
    private String productVersion;

    public Database() {
    }

    public String getProductName() {
        return this.productName;
    }

    public String getProductVersion() {
        return this.productVersion;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public void setProductVersion(final String productVersion) {
        this.productVersion = productVersion;
    }

    @Override
    public String toString() {
        return "Database(productName=" + this.getProductName() + ", productVersion=" + this.getProductVersion() + ")";
    }
}

