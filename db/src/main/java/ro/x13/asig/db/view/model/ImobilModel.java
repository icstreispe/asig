package ro.x13.asig.db.view.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImobilModel {

    private Long id;

    private Long mediu;
    private Long constructie;
    private Long structuraType;

    private BigDecimal suprafata;
    private Integer anConstructie;
    private Integer nrCamere;
    private Integer nrEtaje;
    private Integer nrCladiri;
    private String mentiuni;
    private String vecinatate;

    private List list;
    private List mediuList;
    private List constructieList;
    private List structuraTypeList;

    public void copy(ImobilModel a) {
        id = a.id;

        mediu = a.mediu;
        constructie = a.constructie;
        structuraType = a.structuraType;

        suprafata = a.suprafata;
        anConstructie = a.anConstructie;
        nrCamere = a.nrCamere;
        nrEtaje = a.nrEtaje;
        nrCladiri = a.nrCladiri;
        mentiuni = a.mentiuni;
        vecinatate = a.vecinatate;
    }
}
