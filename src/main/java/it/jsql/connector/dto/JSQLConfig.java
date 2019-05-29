package it.jsql.connector.dto;

public class JSQLConfig {

    private String apiKey;
    private String devKey;

    public JSQLConfig(String apiKey, String devKey) {
        this.apiKey = apiKey;
        this.devKey = devKey;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getDevKey() {
        return devKey;
    }

    public void setDevKey(String devKey) {
        this.devKey = devKey;
    }

}
