
package com.main.firebase;

public class ViewFirebaseDummy extends ViewFirebase{
    
    String currentEmail;
    String currentPass;
    int coins;
    
    void signUp(String email, String pass){
        //data.auth.SignUp(email, pass);
        
        this.currentEmail = email;
        this.currentPass = pass;
    }
    
    void signIn(String email, String pass) {
        //data.auth.SignIn(email, pass);
        
        this.currentEmail = email;
        this.currentPass = pass;
    }
    
    int getCoins(){
        //return data.auth.totalCoins;
        
        return coins;
    }
    
    void incrementCoins(int value){
        //data.auth.incrementCoins(value);
        //data.updateUser();
        
        coins += value;
    }
}
