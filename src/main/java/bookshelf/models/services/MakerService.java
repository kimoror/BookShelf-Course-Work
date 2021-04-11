package bookshelf.models.services;

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
     * @param maker entity to save
     */
    public void save(Maker maker){
        makerRepo.save(maker);
    }

    /**
     * Find all makers
     *
     * @return Makers
     */
    public List<Maker> findAll(){
        return makerRepo.findAll();
    }
}
