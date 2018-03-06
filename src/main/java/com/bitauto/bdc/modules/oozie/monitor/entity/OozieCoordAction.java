package com.bitauto.bdc.modules.oozie.monitor.entity;

/**
 * Created by michealzhang on 2017/8/17.
 */
public class OozieCoordAction {

    private String  coord ="    {\n" +
            "      \"nominalTime\": \"Wed, 16 Aug 2017 20:10:00 GMT\",\n" +
            "      \"trackerUri\": null,\n" +
            "      \"lastModifiedTime\": \"Wed, 16 Aug 2017 20:12:49 GMT\",\n" +
            "      \"coordJobId\": \"0003117-170713174620474-oozie-root-C\",\n" +
            "      \"missingDependencies\": \"\",\n" +
            "      \"createdConf\": null,\n" +
            "      \"errorMessage\": null,\n" +
            "      \"externalId\": \"0021648-170719105023061-oozie-root-W\",\n" +
            "      \"errorCode\": null,\n" +
            "      \"type\": null,\n" +
            "      \"pushMissingDependencies\": null,\n" +
            "      \"consoleUrl\": null,\n" +
            "      \"runConf\": null,\n" +
            "      \"actionNumber\": 34,\n" +
            "      \"createdTime\": \"Wed, 16 Aug 2017 20:07:23 GMT\",\n" +
            "      \"toString\": \"CoordinatorAction name[0003117-170713174620474-oozie-root-C@34] status[SUCCEEDED]\",\n" +
            "      \"id\": \"0003117-170713174620474-oozie-root-C@34\",\n" +
            "      \"externalStatus\": null,\n" +
            "      \"status\": \"SUCCEEDED\"\n" +
            "    }";


    private String nominalTime;
    private String trackerUri;
    private String lastModifiedTime;
    private String coordJobId;
    private String createdConf;
    private String missingDependencies;
    private String errorMessage;
    private String externalId;
    private String errorCode;
    private String type;
    private String pushMissingDependencies;
    private String consoleUrl;
    private String runConf;
    private String actionNumber;
    private String createdTime;
    private String toString;
    private String id;
    private String externalStatus;
    private String status;

    public String getNominalTime() {
        return nominalTime;
    }

    public void setNominalTime(String nominalTime) {
        this.nominalTime = nominalTime;
    }

    public String getTrackerUri() {
        return trackerUri;
    }

    public void setTrackerUri(String trackerUri) {
        this.trackerUri = trackerUri;
    }

    public String getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(String lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getCoordJobId() {
        return coordJobId;
    }

    public void setCoordJobId(String coordJobId) {
        this.coordJobId = coordJobId;
    }

    public String getCreatedConf() {
        return createdConf;
    }

    public void setCreatedConf(String createdConf) {
        this.createdConf = createdConf;
    }

    public String getMissingDependencies() {
        return missingDependencies;
    }

    public void setMissingDependencies(String missingDependencies) {
        this.missingDependencies = missingDependencies;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPushMissingDependencies() {
        return pushMissingDependencies;
    }

    public void setPushMissingDependencies(String pushMissingDependencies) {
        this.pushMissingDependencies = pushMissingDependencies;
    }

    public String getConsoleUrl() {
        return consoleUrl;
    }

    public void setConsoleUrl(String consoleUrl) {
        this.consoleUrl = consoleUrl;
    }

    public String getRunConf() {
        return runConf;
    }

    public void setRunConf(String runConf) {
        this.runConf = runConf;
    }

    public String getActionNumber() {
        return actionNumber;
    }

    public void setActionNumber(String actionNumber) {
        this.actionNumber = actionNumber;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
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

    public String getExternalStatus() {
        return externalStatus;
    }

    public void setExternalStatus(String externalStatus) {
        this.externalStatus = externalStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
