package it.jsql.connector.controller;

import it.jsql.connector.dto.JSQLConfig;
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

    private final String TRANSACTION_ID = "txid";

    @PostMapping("/select")
    public ResponseEntity select(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {
        return new ResponseEntity<>(JSQLConnector.callSelect(data, this.getConfig()), HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity delete(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {
        return new ResponseEntity<>(JSQLConnector.callSelect(data, this.getConfig()), HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {
        return new ResponseEntity<>(JSQLConnector.callSelect(data, this.getConfig()), HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody Map<String, Object> data, @RequestHeader(value = TRANSACTION_ID, required = false) String transactionId, HttpServletResponse response) throws JSQLException {
        return new ResponseEntity<>(JSQLConnector.callSelect(data, this.getConfig()), HttpStatus.OK);
    }

    @PostMapping("/rollback")
    public ResponseEntity rollback(@RequestHeader(TRANSACTION_ID) String transactionId) throws JSQLException {
        return new ResponseEntity<>(JSQLConnector.callRollback(transactionId, this.getConfig()), HttpStatus.OK);
    }

    @PostMapping("/commit")
    public ResponseEntity commit(@RequestHeader(TRANSACTION_ID) String transactionId) throws JSQLException {
        return new ResponseEntity<>(JSQLConnector.callCommit(transactionId, this.getConfig()), HttpStatus.OK);
    }

}
