package ro.x13.asig.db.dao.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name="nt_constructie")
@Data
@SuperBuilder
@NoArgsConstructor
public class ConstructieType extends CatalogDomain {


}
