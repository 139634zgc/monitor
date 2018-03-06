package com.bitauto.bdc.modules.oozieDashboard.model;

/**
 * Created by michealzhang on 2017/8/17.
 */
public class OozieCoordinator {

    private String coord ="  \"coordinatorjobs\": [\n" +
            "    {\n" +
            "      \"mat_throttling\": 0,\n" +
            "      \"executionPolicy\": \"FIFO\",\n" +
            "      \"conf\": null,\n" +
            "      \"acl\": null,\n" +
            "      \"frequency\": \"30 2 * * *\",\n" +
            "      \"total\": 0,\n" +
            "      \"pauseTime\": null,\n" +
            "      \"consoleUrl\": null,\n" +
            "      \"lastAction\": \"Thu, 17 Aug 2017 18:30:00 GMT\",\n" +
            "      \"coordJobName\": \"hive_audit\",\n" +
            "      \"startTime\": \"Fri, 11 Aug 2017 08:03:00 GMT\",\n" +
            "      \"coordExternalId\": null,\n" +
            "      \"timeUnit\": \"CRON\",\n" +
            "      \"group\": null,\n" +
            "      \"coordJobId\": \"0017249-170719105023061-oozie-root-C\",\n" +
            "      \"coordJobPath\": \"hdfs://cube1/user/hue/oozie/deployments/_liuming_-oozie-67291-1502446405.19\",\n" +
            "      \"bundleId\": null,\n" +
            "      \"timeZone\": \"Asia/Shanghai\",\n" +
            "      \"concurrency\": 1,\n" +
            "      \"timeOut\": 120,\n" +
            "      \"nextMaterializedTime\": \"Thu, 17 Aug 2017 18:30:00 GMT\",\n" +
            "      \"toString\": \"Coordinator application id[0017249-170719105023061-oozie-root-C] status[RUNNING]\",\n" +
            "      \"endTime\": \"Tue, 18 Aug 2020 08:03:00 GMT\",\n" +
            "      \"user\": \"liuming\",\n" +
            "      \"actions\": [],\n" +
            "      \"status\": \"RUNNING\"\n" +
            "    }";


    private String mat_throttling;
    private String executionPolicy;
    private String conf;
    private String acl;
    private String frequency;
    private String total;
    private String pauseTime;
    private String consoleUrl;
    private String lastAction;
    private String coordJobName;
    private String startTime;
    private String coordExternalId;
    private String timeUnit;
    private String group;
    private String coordJobId;
    private String coordJobPath;
    private String bundleId;
    private String timeZone;
    private String concurrency;
    private String timeOut;
    private String nextMaterializedTime;
    private String toString;

    private String endTime;
    private String user;
    private String actions;
    private String status;


    public String getMat_throttling() {
        return mat_throttling;
    }

    public void setMat_throttling(String mat_throttling) {
        this.mat_throttling = mat_throttling;
    }

    public String getExecutionPolicy() {
        return executionPolicy;
    }

    public void setExecutionPolicy(String executionPolicy) {
        this.executionPolicy = executionPolicy;
    }

    public String getConf() {
        return conf;
    }

    public void setConf(String conf) {
        this.conf = conf;
    }

    public String getAcl() {
        return acl;
    }

    public void setAcl(String acl) {
        this.acl = acl;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPauseTime() {
        return pauseTime;
    }

    public void setPauseTime(String pauseTime) {
        this.pauseTime = pauseTime;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public String getLastAction() {
        return lastAction;
    }

    public void setLastAction(String lastAction) {
        this.lastAction = lastAction;
    }

    public String getCoordJobName() {
        return coordJobName;
    }

    public void setCoordJobName(String coordJobName) {
        this.coordJobName = coordJobName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getCoordExternalId() {
        return coordExternalId;
    }

    public void setCoordExternalId(String coordExternalId) {
        this.coordExternalId = coordExternalId;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCoordJobId() {
        return coordJobId;
    }

    public void setCoordJobId(String coordJobId) {
        this.coordJobId = coordJobId;
    }

    public String getCoordJobPath() {
        return coordJobPath;
    }

    public void setCoordJobPath(String coordJobPath) {
        this.coordJobPath = coordJobPath;
    }

    public String getBundleId() {
        return bundleId;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getConcurrency() {
        return concurrency;
    }

    public void setConcurrency(String concurrency) {
        this.concurrency = concurrency;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getNextMaterializedTime() {
        return nextMaterializedTime;
    }

    public void setNextMaterializedTime(String nextMaterializedTime) {
        this.nextMaterializedTime = nextMaterializedTime;
    }

    public String getToString() {
        return toString;
    }

    public void setToString(String toString) {
        this.toString = toString;
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
}
