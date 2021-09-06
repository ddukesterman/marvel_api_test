package com.trenton.marvel.account

object AccountUtils {

    fun isValidEmail(email: String): Boolean {
        //a@b.c
        if(email.length < 5) return false

        //missing @ || the . before .com, .org, .gov, etc
        if(!email.contains("@") || !email.contains(".")) return false

        //Starts/Ends with @.  Ex: @trenton.com or trenton.com@
        if(email.startsWith("@") || email.endsWith("@")) return false

        //Starts/Ends with '.'.  Ex: .trenton@com or trenton@com.
        if(email.startsWith(".") || email.endsWith(".")) return false

        //Multiple @
        var atCount = 0;
        email.forEach {
            if(it == '@') atCount++
        }
        if(atCount > 1) return false

        return true
    }

}