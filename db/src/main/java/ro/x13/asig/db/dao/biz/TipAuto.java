package ro.x13.asig.db.dao.biz;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.AsigType;
import ro.x13.asig.db.dao.domain.CatalogDomain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="bn_tip_auto")        //TODO business?
@Data
@SuperBuilder
@NoArgsConstructor
public class TipAuto extends CatalogDomain {


    @ManyToOne
    @JoinColumn(name = "categ_auto", nullable = true)
    private CategorieAuto categorieAuto;

}
