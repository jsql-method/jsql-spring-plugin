package it.jsql.connector.controller;

import com.google.gson.Gson;
import it.jsql.connector.dto.JSQLConfig;
import it.jsql.connector.dto.JSQLResponse;
import it.jsql.connector.exceptions.JSQLException;
import it.jsql.connector.service.JSQLConnector;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Dawid on 2016-09-13.
 * Modified by Michael on 2018-09-10.
 */
@CrossOrigin
@RestController
@RequestMapping("/api/jsql")
public abstract class JSQLController {

    public JSQLController() {
    }

    public abstract JSQLConfig getConfig();

    private static final String API_URL = "https://provider.jsql.it/api/jsql";

    public String getProviderUrl() {
        return API_URL;
    }

    public static final String TRANSACTION_ID = "txid";

    @PostMapping(value = "/select", produces = "application/json", consumes = "application/json")
    public ResponseEntity select(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {

        JSQLResponse jsqlResponse = JSQLConnector.callSelect(transactionId, this.getProviderUrl(), data, this.getConfig());

        if(jsqlResponse.transactionId != null){
            response.setHeader(TRANSACTION_ID, jsqlResponse.transactionId);
        }

        return new ResponseEntity<>(jsqlResponse.response, HttpStatus.OK);
    }

    @PostMapping(value = "/delete", produces = "application/json", consumes = "application/json")
    public ResponseEntity delete(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {


        JSQLResponse jsqlResponse = JSQLConnector.callDelete(transactionId, this.getProviderUrl(), data, this.getConfig());

        if(jsqlResponse.transactionId != null){
            response.setHeader(TRANSACTION_ID, jsqlResponse.transactionId);
        }

        return new ResponseEntity<>(jsqlResponse.response, HttpStatus.OK);

    }

    @PostMapping(value = "/update", produces = "application/json", consumes = "application/json")
    public ResponseEntity update(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {

        JSQLResponse jsqlResponse = JSQLConnector.callUpdate(transactionId, this.getProviderUrl(), data, this.getConfig());

        if(jsqlResponse.transactionId != null){
            response.setHeader(TRANSACTION_ID, jsqlResponse.transactionId);
        }

        return new ResponseEntity<>(jsqlResponse.response, HttpStatus.OK);

    }

    @PostMapping(value = "/insert", produces = "application/json", consumes = "application/json")
    public ResponseEntity insert(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {

        JSQLResponse jsqlResponse = JSQLConnector.callInsert(transactionId, this.getProviderUrl(), data, this.getConfig());

        if(jsqlResponse.transactionId != null){
            response.setHeader(TRANSACTION_ID, jsqlResponse.transactionId);
        }

        return new ResponseEntity<>(jsqlResponse.response, HttpStatus.OK);

    }

    @PostMapping(value = "/rollback", produces = "application/json", consumes = "application/json")
    public ResponseEntity rollback(@RequestHeader(TRANSACTION_ID) String transactionId) throws JSQLException {

        JSQLResponse jsqlResponse = JSQLConnector.callRollback(this.getProviderUrl(), transactionId, this.getConfig());
        return new ResponseEntity<>(jsqlResponse.response, HttpStatus.OK);

    }

    @PostMapping(value = "/commit", produces = "application/json", consumes = "application/json")
    public ResponseEntity commit(@RequestHeader(TRANSACTION_ID) String transactionId) throws JSQLException {

        JSQLResponse jsqlResponse = JSQLConnector.callCommit(this.getProviderUrl(), transactionId, this.getConfig());
        return new ResponseEntity<>(jsqlResponse.response, HttpStatus.OK);

    }

}
