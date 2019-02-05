package it.jsql.connector.controller;

import it.jsql.connector.dto.TransactionThread;
import it.jsql.connector.service.IJSQLService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by Dawid on 2016-09-13.
 * Modified by Michael on 2018-09-10.
 */
public abstract class JSQLController {

    public JSQLController() {
    }

    public abstract IJSQLService getJsqlService();

    private IJSQLService ijsqlService = null;

    private IJSQLService getJsqlServiceSingleton() {

        if (this.ijsqlService == null) {
            this.ijsqlService = this.getJsqlService();
        }

        return this.ijsqlService;

    }

    @RequestMapping(value = "/select", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> select(@RequestBody Map<String, Object> data,
                                            HttpServletResponse response,
                                            @RequestHeader(value = "TX", required = false, defaultValue = "false") Boolean isTransactional,
                                            @RequestHeader(value = "TXID", required = false) String txid) {

        TransactionThread transactionThread = new TransactionThread(data, isTransactional, txid);

        this.getJsqlServiceSingleton().select(transactionThread);

        if (isTransactional) {

            response.addHeader("TXID", transactionThread.getTransactionId());

        }

        return transactionThread.getResponse();
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> delete(@RequestBody Map<String, Object> data,
                                            HttpServletResponse response,
                                            @RequestHeader(value = "TX", required = false, defaultValue = "false") Boolean isTransactional,
                                            @RequestHeader(value = "TXID", required = false) String txid) {

        TransactionThread transactionThread = new TransactionThread(data, isTransactional, txid);

        this.getJsqlServiceSingleton().delete(transactionThread);

        if (isTransactional) {

            response.addHeader("TXID", transactionThread.getTransactionId());

        }

        return transactionThread.getResponse();
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> update(@RequestBody Map<String, Object> data,
                                            HttpServletResponse response,
                                            @RequestHeader(value = "TX", required = false, defaultValue = "false") Boolean isTransactional,
                                            @RequestHeader(value = "TXID", required = false) String txid) {

        TransactionThread transactionThread = new TransactionThread(data, isTransactional, txid);

        this.getJsqlServiceSingleton().update(transactionThread);

        if (isTransactional) {

            response.addHeader("TXID", transactionThread.getTransactionId());

        }

        return transactionThread.getResponse();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<Map<String, Object>> insert(@RequestBody Map<String, Object> data,
                                            HttpServletResponse response,
                                            @RequestHeader(value = "TX", required = false, defaultValue = "false") Boolean isTransactional,
                                            @RequestHeader(value = "TXID", required = false) String txid) {

        TransactionThread transactionThread = new TransactionThread(data, isTransactional, txid);

        this.getJsqlServiceSingleton().insert(transactionThread);

        if (isTransactional) {

            response.addHeader("TXID", transactionThread.getTransactionId());

        }

        return transactionThread.getResponse();
    }

    @RequestMapping(value = "/commit", method = RequestMethod.GET, produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, String> commit(@RequestHeader("TXID") String txid) {

        return this.getJsqlServiceSingleton().commitTransaction(txid);
    }


}
