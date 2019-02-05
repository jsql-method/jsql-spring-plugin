package it.jsql.connector.dto;

import java.util.List;
import java.util.Map;

/**
 * Created by Dawid on 2016-09-13.
 * Modified by Michael on 2018-09-10.
 */
public class TransactionThread {

    private Map<String, Object> request;
    private List<Map<String, Object>> response;
    private String transactionId;
    private Boolean isTransactional;
    private Boolean paramsAsArray;

    public TransactionThread() {
    }

    public TransactionThread(Map<String, Object> request, Boolean isTransactional, String transactionId) {
        this.request = request;
        this.isTransactional = isTransactional;
        this.transactionId = transactionId;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public List<Map<String, Object>> getResponse() {
        return response;
    }

    public void setResponse(List<Map<String, Object>> response) {
        this.response = response;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Boolean getTransactional() {
        return isTransactional;
    }

    public void setTransactional(Boolean transactional) {
        isTransactional = transactional;
    }

    public Boolean getParamsAsArray() {
        return paramsAsArray;
    }

    public void setParamsAsArray(Boolean paramsAsArray) {
        this.paramsAsArray = paramsAsArray;
    }

}
