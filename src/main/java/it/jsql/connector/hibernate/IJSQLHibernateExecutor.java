package it.jsql.connector.hibernate;

import it.jsql.connector.dto.JSQLQueryType;
import it.jsql.connector.dto.TransactionThread;

import java.util.Map;

/**
 * Created by Dawid on 2016-09-12.
 * Modified by Michael on 2018-09-10.
 */
public interface IJSQLHibernateExecutor {

    void executeSelect(String sql, Map<String, Object> params, TransactionThread transactionThread);

    void executeDelete(String sql, Map<String, Object> params, TransactionThread transactionThread);

    void executeUpdate(String sql, Map<String, Object> params, TransactionThread transactionThread);

    void executeInsert(String sql, Map<String, Object> params, TransactionThread transactionThread);

    Map<String, String> commit(String txid);

}
