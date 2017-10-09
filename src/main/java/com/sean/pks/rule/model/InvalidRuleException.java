package com.sean.pks.rule.model;

import java.io.IOException;

/** 
 * Exception: rule condition or operation is not correct
 * @author sean
 *
 */
public class InvalidRuleException extends IOException{
    private static final long serialVersionUID = 1L; 
    public InvalidRuleException() { 
        super("Invalid Rule"); 
    } 
}
