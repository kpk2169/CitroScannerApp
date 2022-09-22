package com.sekim.citroscanner.Utils;

import java.util.regex.Pattern;

public class PatternList {

    public static Pattern getEmailPattern(){
        return Pattern.compile("^[a-zA-X0-9]@[a-zA-Z0-9].[a-zA-Z0-9]");
    }

}
