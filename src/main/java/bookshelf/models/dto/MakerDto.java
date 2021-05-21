package bookshelf.models.dto;

import bookshelf.models.entities.Maker;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Dto of {@link Maker}
 */
@Setter
@Getter
@NoArgsConstructor
@ToString
public class MakerDto {
    private long id;
    private String name;

    public MakerDto(Maker maker){
        this.id = maker.getId();
        this.name = maker.getName();
    };
}