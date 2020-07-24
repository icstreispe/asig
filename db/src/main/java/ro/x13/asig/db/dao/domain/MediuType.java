package ro.x13.asig.db.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="nt_mediu")
@Data
@SuperBuilder
@NoArgsConstructor
public class MediuType extends  CatalogDomain{



}
