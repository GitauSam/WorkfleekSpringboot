package com.comulynx.wallet.rest.api.exception;

public class WebUserWithCustomerIdExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public WebUserWithCustomerIdExistsException(String message){
        super(message);
    }

}
