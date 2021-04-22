package com.comulynx.wallet.rest.api.exception;

public class CustomerWithCustomerIdExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public CustomerWithCustomerIdExistsException(String message){
        super(message);
    }
}
