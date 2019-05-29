package it.jsql.connector.exceptions;

/**
 * Created by Dawid on 2016-09-13.
 * Modified by Michael on 2018-09-10.
 */
public class JSQLException extends Exception {

    public JSQLException(){
        super();
    }

    public JSQLException(String message){
        super("JSQL: "+message);
    }

}
