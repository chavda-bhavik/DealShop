/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.security.enterprise.authentication.mechanism.http.CustomFormAuthenticationMechanismDefinition;
import javax.security.enterprise.authentication.mechanism.http.LoginToContinue;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author bhavik
 */
//@Named
//@CustomFormAuthenticationMechanismDefinition(
//    loginToContinue = @LoginToContinue(
//            loginPage = "/user/Login.jsf"
//    )
//)
@ApplicationScoped
@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "jdbc/dealshop",
    callerQuery = "select Password from usertb where Email=?",
    groupsQuery = "select category.Name from usercategorytb category, usertb user where user.UserCategoryID=category.CategoryID and user.Email=?",
    hashAlgorithm = Pbkdf2PasswordHash.class,
    priority = 30
)
public class ProjectConfig {
    
}
