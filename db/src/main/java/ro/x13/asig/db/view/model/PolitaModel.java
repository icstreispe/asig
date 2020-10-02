package ro.x13.asig.db.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolitaModel {

    private Long id;
    private String serie;
    private Integer nr;
    private Long societate;
    private BigDecimal sumaAsig;
    private Long moneda;
    private Long tipPlata;
    private Long produs;

    private Date startValid;
    private Date endValid;
    private Date emisLa;

    private List produsList;
    private List societateList;
    private List monedaList;

    private List list;
}
