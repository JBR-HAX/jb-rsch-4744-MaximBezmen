package org.jetbrains.assignment.entity;


import com.vladmihalcea.hibernate.type.json.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
import org.jetbrains.assignment.dto.MovementDTO;
import org.jetbrains.assignment.dto.PositionDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;


@Entity
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonType.class)
})
public class Robot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<MovementDTO> movements;

    @Type(type = "json")
    @Column(columnDefinition = "json")
    private List<PositionDTO> positions;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public List<MovementDTO> getMovements() {
        return movements;
    }

    public void setMovements(final List<MovementDTO> movements) {
        this.movements = movements;
    }

    public List<PositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(final List<PositionDTO> positions) {
        this.positions = positions;
    }
}
