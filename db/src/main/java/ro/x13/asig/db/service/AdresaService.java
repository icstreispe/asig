package ro.x13.asig.db.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.x13.asig.db.dao.AdresaRepository;
import ro.x13.asig.db.dao.domain.Adresa;
import ro.x13.asig.db.dao.domain.Judet;
import ro.x13.asig.db.dao.domain.Tara;
import ro.x13.asig.db.dao.domain.TipStrada;
import ro.x13.asig.db.service.model.AdresaModel;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdresaService {

    @Autowired
    private AdresaRepository adresaRepository;
    @Autowired
    private TaraService taraService;
    @Autowired
    private JudetService judetService;
    @Autowired
    private TipStradaService tipStradaService;

    @Transactional
    public void save(AdresaModel adresaModel) {
        Adresa a = buildAdresa(adresaModel);
        adresaRepository.save(a);
    }

    private Adresa buildAdresa(AdresaModel adresaModel) {
        Tara tara = taraService.get(adresaModel.getTara());
        Judet judet = judetService.get(adresaModel.getJudet());
        TipStrada tipStrada = tipStradaService.get(adresaModel.getTipStrada());
        return Adresa.builder()
                .id(adresaModel.getId())
                .scara(adresaModel.getScara())
                .tara(tara)
                .tipStrada(tipStrada)
                .strada(adresaModel.getStrada())
                .apartament(adresaModel.getApartament())
                .bloc(adresaModel.getBloc())
                .codPostal(adresaModel.getCodPostal())
                .etaj(adresaModel.getEtaj())
                .judet(judet)
                .numar(adresaModel.getNumar())
                .localitate(adresaModel.getLocalitate())
                .sublocalitate(adresaModel.getSublocalitate())
                .build();
    }


    private AdresaModel toModel(Adresa adresa) {
        return AdresaModel.builder()
                .id(adresa.getId())
                .scara(adresa.getScara())
                .tara(adresa.getTara() == null ? null : adresa.getTara().getId())
                .tipStrada(adresa.getTipStrada() == null ? null : adresa.getTipStrada().getId())
                .strada(adresa.getStrada())
                .apartament(adresa.getApartament())
                .bloc(adresa.getBloc())
                .codPostal(adresa.getCodPostal())
                .etaj(adresa.getEtaj())
                .judet(adresa.getJudet() == null ? null : adresa.getJudet().getId())
                .numar(adresa.getNumar())
                .localitate(adresa.getLocalitate())
                .sublocalitate(adresa.getSublocalitate()).build();
    }

    private Map toView(Adresa adresa) {
        Map m = new HashMap();
        m.put("id", adresa.getId());
        m.put("scara", adresa.getScara());
        m.put("tara", adresa.getTara() == null ? null : adresa.getTara().getName());
        m.put("tipStrada", adresa.getTipStrada() == null ? null : adresa.getTipStrada().getName());
        m.put("strada", adresa.getStrada());
        m.put("apartament", adresa.getApartament());
        m.put("bloc", adresa.getBloc());
        m.put("codPostal", adresa.getCodPostal());
        m.put("etaj", adresa.getEtaj());
        m.put("judet", adresa.getJudet() == null ? null : adresa.getJudet().getName());
        m.put("numar", adresa.getNumar());
        m.put("localitate", adresa.getLocalitate());
        m.put("sublocalitate", adresa.getSublocalitate());
        return m;
    }

    public List<Map> list() {
        List<Adresa> list = adresaRepository.findAllByOrderById();
        return StreamSupport.stream(list.spliterator(), false)
                .map(a -> toView(a))       // pt list(lista()): Adresa::toMap
                .collect(Collectors.toList());
    }


    public AdresaModel load(Long id) {
        Optional<ro.x13.asig.db.dao.domain.Adresa> adresa = adresaRepository.findById(id);
        return toModel(adresa.get());
    }


}
