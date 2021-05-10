package bookshelf.models.services;

import bookshelf.aspect.Loggable;
import bookshelf.models.entities.Maker;
import bookshelf.models.repository.MakerRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class MakerService{
    private final MakerRepo makerRepo;

    public MakerService(MakerRepo makerRepo) {
        this.makerRepo = makerRepo;
    }

    /**
     * Find all makers
     *
     * @return Makers
     */

    @Transactional
    @Loggable
    public List<Maker> findAll(){
        return makerRepo.findAll();
    }
}
