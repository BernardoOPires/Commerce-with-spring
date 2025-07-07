package com.devsuperior.DsCommerce.services.Exceptions;

public class DatabaseException extends RuntimeException{
    
    public DatabaseException(String msg){
         super(msg);
    }
}
