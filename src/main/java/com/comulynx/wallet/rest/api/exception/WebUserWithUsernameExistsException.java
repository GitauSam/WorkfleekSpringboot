package com.comulynx.wallet.rest.api.exception;

public class WebUserWithUsernameExistsException extends Exception{

    private static final long serialVersionUID = 1L;

    public WebUserWithUsernameExistsException(String message){
        super(message);
    }

}
