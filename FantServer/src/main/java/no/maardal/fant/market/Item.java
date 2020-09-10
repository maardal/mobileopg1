package no.maardal.fant.market;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import static no.maardal.fant.market.Item.FIND_ALL_ITEMS;
import static no.maardal.fant.market.Item.FIND_ITEM_BY_IDS;

@Entity @Table(name = "AITEM")
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@NamedQuery(name = FIND_ITEM_BY_IDS, query = "select i from Item i order by i.itemid")
@NamedQuery(name = FIND_ALL_ITEMS, query = "select i from Item i")
public class Item implements Serializable{
    public static final String FIND_ITEM_BY_IDS = "Item.findItemByIds";
    public static final String FIND_ALL_ITEMS = "Item.findAllItems";
    
    @Version
    Timestamp version;

    @Temporal(javax.persistence.TemporalType.DATE)
    Date created;
    
    @Id @GeneratedValue
    String itemid;
 
    @NotEmpty
    String title;
    
    @NotEmpty
    String description;
    
    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer= 10 ,fraction = 2)
    BigDecimal price;
    
    @PrePersist
    protected void onCreate() {
        created = new Date();
    }
    
}
