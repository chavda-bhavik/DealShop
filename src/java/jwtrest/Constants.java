
package jwtrest;

import javax.inject.Named;

//@Named
public final class Constants {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    public static final String BEARER = "Bearer ";
    public static final String FILE_UPLOAD = "/home/bhavik/Desktop/DealShop/web/assets/images/";
    
    //"/media/bhavik/DATA1/Sem8/DealShop/DealShop-war/web/assets/images/"
//
//    public static final String ADMIN = "Admin";
//
//    public static final String USER = "User";

    public static final int REMEMBERME_VALIDITY_SECONDS = 24 * 60 * 60; //24 hours

    // Load the TokenProvider configuration[secretKey,tokenValidity] and REMEMBERME_VALIDITY_SECONDS from config
}
