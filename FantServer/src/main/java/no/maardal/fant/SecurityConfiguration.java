package no.maardal.fant;

import javax.annotation.security.DeclareRoles;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.PasswordHash;
import no.maardal.fant.auth.Group;
import org.eclipse.microprofile.auth.LoginConfig;

@DatabaseIdentityStoreDefinition(
    dataSourceLookup=DataSourceProducer.JNDI_NAME,
    callerQuery="select password from auser where userid = ?",
    groupsQuery="select name from ausergroup where userid  = ?",
    hashAlgorithm = PasswordHash.class,
    priority = 80)
@DeclareRoles({Group.ADMIN,Group.USER})
@LoginConfig(authMethod = "MP-JWT",realmName = "template")
public class SecurityConfiguration {    
}
