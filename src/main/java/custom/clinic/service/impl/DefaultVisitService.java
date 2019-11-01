package custom.clinic.service.impl;

import custom.clinic.dao.VisitDao;
import custom.clinic.model.Visit;
import custom.clinic.service.VisitService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DefaultVisitService implements VisitService {

    @Resource
    private VisitDao visitDao;

    @Override
    public void save(Visit visit) {
        visitDao.save(visit);
    }
}
