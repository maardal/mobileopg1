package no.maardal.fant.market;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity @Table(name = "ITEM")
@Data @AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
public class Item implements Serializable{
    public static final String FIND_ITEM_BY_IDS = "Item.findItemByIds";
    public static final String FIND_ALL_ITEMS = "Item.findAllItems";
    
    @Id
    private long itemId;
    
    private String title;
    private String description;
    private BigDecimal price;
    
}
