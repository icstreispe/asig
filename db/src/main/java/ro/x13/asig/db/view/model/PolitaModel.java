package ro.x13.asig.db.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    private BigDecimal sumaAsig;
    private Long societate;
    private Long tipPlata;

    private Date startValid;
    private Date endValid;
    private Date emisLa;

    private List societateList;

    private List list;
}
