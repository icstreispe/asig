package ro.x13.asig.db.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PolitaModel {

    private String serie;
    private Integer nr;
    private Date startValid;
    private Date endValid;
}
