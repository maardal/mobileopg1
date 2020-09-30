
package no.maardal.fant.market;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import no.maardal.fant.auth.User;
import static no.maardal.fant.market.Purchase.FIND_ALL_PURCHASES;
import static no.maardal.fant.market.Purchase.FIND_PURCHASE_BY_ID;

@Entity @Table(name = "PURCHASE")
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@NamedQuery(name = FIND_PURCHASE_BY_ID, query = "select p from Purchase p where p.purchaseid = :purchaseid")
@NamedQuery(name = FIND_ALL_PURCHASES, query = "select p from Purchase p")
public class Purchase implements Serializable {
    public static final String FIND_PURCHASE_BY_ID = "Purchase.findPurchaseByIds";
    public static final String FIND_ALL_PURCHASES = "Purchase.findAllPurchases";
    
    @Version
    Timestamp version;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    Date purchaseDate;
    
    @Id @GeneratedValue
    String purchaseid;
    
    @NotNull
    String itemid;
    
    @NotNull
    User seller;
    
    @NotNull
    User buyer;
    
    @PrePersist
    protected void onCreate() {
        purchaseDate = new Date();
    }
}
