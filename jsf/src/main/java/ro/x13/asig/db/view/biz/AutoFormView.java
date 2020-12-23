package ro.x13.asig.db.view.biz;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ro.x13.asig.db.dao.biz.*;
import ro.x13.asig.db.dao.domain.Auto;
import ro.x13.asig.db.filter.Cacheable;
import ro.x13.asig.db.filter.RequestScopedController;
import ro.x13.asig.db.service.*;

import java.util.List;

import static ro.x13.asig.db.ViewUtil.redirect;

@RequestScopedController("auto")
@RequiredArgsConstructor
@Getter
@Setter
public class AutoFormView {

    private Long id;

    private Long marca;
    private Long categorieAuto;
    private Long tipAuto;
    private Long stareMatric;
    private Long combustibil;
    private Long utilizare;

    private String model;
    private String nrMatric;
    private String serieSasiu;
    private String serieCiv;

    private Integer nrLocuri;
    private Integer masaMax;
    private Integer cilindree;
    private Integer putere;
    private Integer anFabricatie;

    private final AutoService autoService;
    private final CategorieAutoService categorieAutoService;
    private final TipAutoService tipAutoService;
    private final MarcaService marcaService;
    private final CombustibilService combustibilService;
    private final UtilizareTypeService utilizareTypeService;
    private final StareMatricService stareMatricService;

    //called on loading, but also on ajax and save....
    public void setId (Long s){
        id = s;
        Auto auto = autoService.load(id);
        toModel(auto);
    }



    public String save() {
        Auto auto = buildDomain();
        autoService.save(auto);
        return redirect("list");
    }

    @Cacheable
    public List getMarcaList (){
        return marcaService.listCombo();
    }
    @Cacheable
    public List getCategorieAutoList (){
        return categorieAutoService.listCombo();
    }
    @Cacheable
    public List getTipAutoList (){
        if (categorieAuto != null){
            return tipAutoService.listCombo(categorieAuto);
        }
        return tipAutoService.listCombo();
    }
    @Cacheable
    public List getCombustibilList (){
        return combustibilService.listCombo();
    }
    @Cacheable
    public List getUtilizareList (){
        return utilizareTypeService.listCombo();
    }
    @Cacheable
    public List getStareMatricList (){
        return stareMatricService.listCombo();
    }


    private Auto buildDomain() {
        CategorieAuto categorieAuto = categorieAutoService.get(getCategorieAuto());
        TipAuto tipAuto = tipAutoService.get(getTipAuto());
        Combustibil combustibil = combustibilService.get(getCombustibil());
        UtilizareType utilizare = utilizareTypeService.get(getUtilizare());
        Marca marca = marcaService.get(getMarca());
        StareMatric stareMatric = stareMatricService.get(getStareMatric());
        return Auto.builder()
                .id(getId())
                .anFabricatie(getAnFabricatie())
                .cilindree(getCilindree())
                .categorieAuto(categorieAuto)
                .tipAuto(tipAuto)
                .combustibil(combustibil)
                .utilizare(utilizare)
                .marca(marca)
                .stareMatric(stareMatric)
                .masaMax(getMasaMax())
                .model(getModel())
                .nrLocuri(getNrLocuri())
                .nrMatric(getNrMatric())
                .serieCiv(getSerieCiv())
                .serieSasiu(getSerieSasiu())
                .putere(getPutere())
                .build();
    }

    private void toModel(Auto auto) {
                id = auto.getId();
                anFabricatie = auto.getAnFabricatie();
                cilindree = auto.getCilindree();
                categorieAuto = auto.getCategorieAuto() == null ? null : auto.getCategorieAuto().getId();
                tipAuto = auto.getTipAuto() == null ? null : auto.getTipAuto().getId();
                marca = auto.getMarca() == null ? null : auto.getMarca().getId();
                stareMatric = auto.getStareMatric() == null ? null : auto.getStareMatric().getId();
                utilizare = auto.getUtilizare() == null ? null : auto.getUtilizare().getId();
                combustibil = auto.getCombustibil() == null ? null : auto.getCombustibil().getId();
                masaMax = auto.getMasaMax();
                model = auto.getModel();
                nrLocuri = auto.getNrLocuri();
                nrMatric = auto.getNrMatric();
                serieCiv = auto.getSerieCiv();
                serieSasiu = auto.getSerieSasiu();
                putere = auto.getPutere();
    }
}
