package com.comulynx.wallet.rest.api.exception;

public class WebUserWithEmailIdExistsException extends Exception {

    private static final long serialVersionUID = 1L;

    public WebUserWithEmailIdExistsException(String message){
        super(message);
    }

}
