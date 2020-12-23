package ro.x13.asig.db.dao.domain.meta;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ro.x13.asig.db.dao.domain.BaseDomain;

import javax.persistence.*;

@Entity
@Table(name="m_action_flow")
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class ActionFlow extends BaseDomain {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "start_action")
    private Action startAction;

    @ManyToOne
    @JoinColumn(name = "end_action")
    private Action endAction;


}
