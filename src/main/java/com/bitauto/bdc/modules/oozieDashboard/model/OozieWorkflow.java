package com.bitauto.bdc.modules.oozieDashboard.model;

/**
 * Created by michealzhang on 2017/8/17.
 */
public class OozieWorkflow {

    private    String     workflows="{\n" +
            "  \"total\": 17613,\n" +
            "  \"offset\": 51,\n" +
            "  \"len\": 2,\n" +
            "  \"workflows\": [\n" +
            "    {\n" +
            "      \"appName\": \"t2pdm_t05_bjdq_pv_log\",\n" +
            "      \"externalId\": null,\n" +
            "      \"conf\": null,\n" +
            "      \"run\": 0,\n" +
            "      \"acl\": null,\n" +
            "      \"appPath\": null,\n" +
            "      \"parentId\": \"0016337-170719105022944-oozie-root-C@16\",\n" +
            "      \"lastModTime\": \"Wed, 16 Aug 2017 01:07:17 GMT\",\n" +
            "      \"consoleUrl\": \"http://IDC011R01:11000/oozie?job=0021547-170719105023061-oozie-root-W\",\n" +
            "      \"createdTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
            "      \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
            "      \"toString\": \"Workflow id[0021547-170719105023061-oozie-root-W] status[SUCCEEDED]\",\n" +
            "      \"id\": \"0021547-170719105023061-oozie-root-W\",\n" +
            "      \"endTime\": \"Wed, 16 Aug 2017 01:07:17 GMT\",\n" +
            "      \"user\": \"hejianglong\",\n" +
            "      \"actions\": [],\n" +
            "      \"status\": \"SUCCEEDED\",\n" +
            "      \"group\": null\n" +
            "    },\n" +
            "    {\n" +
            "      \"appName\": \"t2pdm_t01_huimaicheapp_device_gen_dt\",\n" +
            "      \"externalId\": null,\n" +
            "      \"conf\": null,\n" +
            "      \"run\": 0,\n" +
            "      \"acl\": null,\n" +
            "      \"appPath\": null,\n" +
            "      \"parentId\": \"0014288-170719105022944-oozie-root-C@16\",\n" +
            "      \"lastModTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
            "      \"consoleUrl\": \"http://IDC011R01:11000/oozie?job=0021548-170719105023061-oozie-root-W\",\n" +
            "      \"createdTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
            "      \"startTime\": \"Wed, 16 Aug 2017 01:00:01 GMT\",\n" +
            "      \"toString\": \"Workflow id[0021548-170719105023061-oozie-root-W] status[SUCCEEDED]\",\n" +
            "      \"id\": \"0021548-170719105023061-oozie-root-W\",\n" +
            "      \"endTime\": \"Wed, 16 Aug 2017 01:09:37 GMT\",\n" +
            "      \"user\": \"hejianglong\",\n" +
            "      \"actions\": [],\n" +
            "      \"status\": \"SUCCEEDED\",\n" +
            "      \"group\": null\n" +
            "    }\n" +
            "  ]\n" +
            "}";


    private String appName;
    private String externalId;
    private String conf;
    private String run;
    private String acl;
    private String appPath;
    private String parentId;
    private String lastModTime;
    private String consoleUrl;
    private String createdTime;
    private String startTime;
    private String toString;
    private String id;
    private String endTime;
    private String user;
    private String actions;
    private String status;
    private String group;


    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getRun() {
        return run;
    }

    public void setRun(String run) {
        this.run = run;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getAppPath() {
        return appPath;
    }

    public void setAppPath(String appPath) {
        this.appPath = appPath;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLastModTime() {
        return lastModTime;
    }

    public void setLastModTime(String lastModTime) {
        this.lastModTime = lastModTime;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
