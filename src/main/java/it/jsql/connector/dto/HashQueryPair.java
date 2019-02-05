package it.jsql.connector.dto;

import java.io.Serializable;

/**
 * Created by Dawid on 2016-09-13.
 * Modified by Michael on 2018-09-10.
 */
public class HashQueryPair implements Serializable{

    String token;
    String query;

    public HashQueryPair(){
    }

    public HashQueryPair(String token, String query) {
        this.token = token;
        this.query = query;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

}
