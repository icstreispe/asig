package ro.x13.asig.view.model;

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
public class LocalitateModel {

    private Long id;
    private String name;

    private Double latitude;
    private Double longitude;
    private Long judet;
    private Long population;
    private Long firms;
    private String apartenenta;


    private List judetList;

    private List list;
}
