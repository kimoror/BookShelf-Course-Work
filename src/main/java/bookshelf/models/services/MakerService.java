package bookshelf.models.services;

import bookshelf.exceptions.EntityNotFoundException;
import bookshelf.models.dto.DtoConverter;
import bookshelf.models.dto.MakerDto;
import bookshelf.models.entities.Maker;
import bookshelf.models.repository.MakerRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MakerService{
    private final MakerRepo makerRepo;

    public MakerService(MakerRepo makerRepo) {
        this.makerRepo = makerRepo;
    }

    /**
     *
     * @param makerDto dto to save
     */
    public Maker save(MakerDto makerDto) {
        if (makerDto.getName() == null || makerDto.getName().equals(""))
            return null;
        return makerRepo.save(DtoConverter.dtoToMaker(makerDto));
    }
    /**
     * Find all makers
     *
     * @return Makers
     */

    public List<Maker> findAll(){
        return makerRepo.findAll();
    }

    public Maker findMakerById(long id){
        return makerRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Maker with id '%s' doesn't found", id)));
    }
}
