package it.jsql.connector.service;

import it.jsql.connector.dto.TransactionThread;

import java.util.List;
import java.util.Map;

/**
 * Created by Dawid on 2016-09-13.
 * Modified by Michael on 2018-09-10.
 */
public interface IJSQLService {

    String LAST_ID_NAME = "lastId";
    String PARAMS_NAME = "params";
    String HASH_NAME = "token";

    void select(TransactionThread transactionThread);

    void delete(TransactionThread transactionThread);

    void update(TransactionThread transactionThread);

    void insert(TransactionThread transactionThread);

    Map<String, String> commitTransaction(String txid);

    List<String> getHash(Map<String, Object> data);

    Map<String, Object> getParamsMap(Map<String, Object> data);

    String getSQLQuery(Map<String, Object> data);

}
