package com.wreckj;

import java.io.Serializable;

/**
 * Created by jagsir on 24/04/15.
 */
class NotSupportedException extends Exception implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 3585778374406474272L;
    String message;
    NotSupportedException() {

    }
    NotSupportedException(String message) {
        this.setMessage(message);
    }
    void setMessage(String message){
        this.message = message;
    }
}