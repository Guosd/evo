package com.github.framework.evo.code.generator;


import java.util.Arrays;

public class GenConfig {
    private String basePackage;
    protected String saveDir;
    protected String saveDirForVo;
    protected String saveDirForXml;
    private GenType[] genTypes;
    private String insertTimeForHisName;
    private String operateTimeForHisName;
    private String versionName;
    protected String[] ignoreTablePrefixs;
    protected boolean keepPrefixForPO = true;
    protected boolean deletedFlagMode;
    protected boolean ignoreUnderline;
    protected boolean useFirstCloumnAsPK;
    protected String[] ignoreColumnNamesWhenUpdate;
    protected String[] tableNames;
    protected boolean defaultCache;
    protected boolean dbPrefix;
    protected boolean dbColumnUnderline;
    protected String dbDriverName;
    protected String dbUser;
    protected String dbPassword;
    protected String dbUrl;
    protected String dbCatalog;
    protected String dbSchema;

    public GenConfig() {
    }

    public String getBasePackage() {
        return this.basePackage;
    }

    public String getSaveDir() {
        return this.saveDir;
    }

    public String getSaveDirForVo() {
        return this.saveDirForVo;
    }

    public String getSaveDirForXml() {
        return this.saveDirForXml;
    }

    public GenType[] getGenTypes() {
        return this.genTypes;
    }

    public String getInsertTimeForHisName() {
        return this.insertTimeForHisName;
    }

    public String getOperateTimeForHisName() {
        return this.operateTimeForHisName;
    }

    public String getVersionName() {
        return this.versionName;
    }

    public String[] getIgnoreTablePrefixs() {
        return this.ignoreTablePrefixs;
    }

    public boolean isKeepPrefixForPO() {
        return this.keepPrefixForPO;
    }

    public boolean isDeletedFlagMode() {
        return this.deletedFlagMode;
    }

    public boolean isIgnoreUnderline() {
        return this.ignoreUnderline;
    }

    public boolean isUseFirstCloumnAsPK() {
        return this.useFirstCloumnAsPK;
    }

    public String[] getIgnoreColumnNamesWhenUpdate() {
        return this.ignoreColumnNamesWhenUpdate;
    }

    public String[] getTableNames() {
        return this.tableNames;
    }

    public boolean isDefaultCache() {
        return this.defaultCache;
    }

    public boolean isDbPrefix() {
        return this.dbPrefix;
    }

    public boolean isDbColumnUnderline() {
        return this.dbColumnUnderline;
    }

    public String getDbDriverName() {
        return this.dbDriverName;
    }

    public String getDbUser() {
        return this.dbUser;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }

    public String getDbUrl() {
        return this.dbUrl;
    }

    public String getDbCatalog() {
        return this.dbCatalog;
    }

    public String getDbSchema() {
        return this.dbSchema;
    }

    public void setBasePackage(final String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSaveDir(final String saveDir) {
        this.saveDir = saveDir;
    }

    public void setSaveDirForVo(final String saveDirForVo) {
        this.saveDirForVo = saveDirForVo;
    }

    public void setSaveDirForXml(final String saveDirForXml) {
        this.saveDirForXml = saveDirForXml;
    }

    public void setGenTypes(final GenType[] genTypes) {
        this.genTypes = genTypes;
    }

    public void setInsertTimeForHisName(final String insertTimeForHisName) {
        this.insertTimeForHisName = insertTimeForHisName;
    }

    public void setOperateTimeForHisName(final String operateTimeForHisName) {
        this.operateTimeForHisName = operateTimeForHisName;
    }

    public void setVersionName(final String versionName) {
        this.versionName = versionName;
    }

    public void setIgnoreTablePrefixs(final String[] ignoreTablePrefixs) {
        this.ignoreTablePrefixs = ignoreTablePrefixs;
    }

    public void setKeepPrefixForPO(final boolean keepPrefixForPO) {
        this.keepPrefixForPO = keepPrefixForPO;
    }

    public void setDeletedFlagMode(final boolean deletedFlagMode) {
        this.deletedFlagMode = deletedFlagMode;
    }

    public void setIgnoreUnderline(final boolean ignoreUnderline) {
        this.ignoreUnderline = ignoreUnderline;
    }

    public void setUseFirstCloumnAsPK(final boolean useFirstCloumnAsPK) {
        this.useFirstCloumnAsPK = useFirstCloumnAsPK;
    }

    public void setIgnoreColumnNamesWhenUpdate(final String[] ignoreColumnNamesWhenUpdate) {
        this.ignoreColumnNamesWhenUpdate = ignoreColumnNamesWhenUpdate;
    }

    public void setTableNames(final String[] tableNames) {
        this.tableNames = tableNames;
    }

    public void setDefaultCache(final boolean defaultCache) {
        this.defaultCache = defaultCache;
    }

    public void setDbPrefix(final boolean dbPrefix) {
        this.dbPrefix = dbPrefix;
    }

    public void setDbColumnUnderline(final boolean dbColumnUnderline) {
        this.dbColumnUnderline = dbColumnUnderline;
    }

    public void setDbDriverName(final String dbDriverName) {
        this.dbDriverName = dbDriverName;
    }

    public void setDbUser(final String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPassword(final String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void setDbUrl(final String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public void setDbCatalog(final String dbCatalog) {
        this.dbCatalog = dbCatalog;
    }

    public void setDbSchema(final String dbSchema) {
        this.dbSchema = dbSchema;
    }

    @Override
    public String toString() {
        return "GenConfig(basePackage=" + this.getBasePackage() + ", saveDir=" + this.getSaveDir() + ", saveDirForVo=" + this.getSaveDirForVo() + ", saveDirForXml=" + this.getSaveDirForXml() + ", genTypes=" + Arrays.deepToString(this.getGenTypes()) + ", insertTimeForHisName=" + this.getInsertTimeForHisName() + ", operateTimeForHisName=" + this.getOperateTimeForHisName() + ", versionName=" + this.getVersionName() + ", ignoreTablePrefixs=" + Arrays.deepToString(this.getIgnoreTablePrefixs()) + ", keepPrefixForPO=" + this.isKeepPrefixForPO() + ", deletedFlagMode=" + this.isDeletedFlagMode() + ", ignoreUnderline=" + this.isIgnoreUnderline() + ", useFirstCloumnAsPK=" + this.isUseFirstCloumnAsPK() + ", ignoreColumnNamesWhenUpdate=" + Arrays.deepToString(this.getIgnoreColumnNamesWhenUpdate()) + ", tableNames=" + Arrays.deepToString(this.getTableNames()) + ", defaultCache=" + this.isDefaultCache() + ", dbPrefix=" + this.isDbPrefix() + ", dbColumnUnderline=" + this.isDbColumnUnderline() + ", dbDriverName=" + this.getDbDriverName() + ", dbUser=" + this.getDbUser() + ", dbPassword=" + this.getDbPassword() + ", dbUrl=" + this.getDbUrl() + ", dbCatalog=" + this.getDbCatalog() + ", dbSchema=" + this.getDbSchema() + ")";
    }
}
