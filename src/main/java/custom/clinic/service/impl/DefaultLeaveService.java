package custom.clinic.service.impl;

import custom.clinic.dao.LeaveDao;
import custom.clinic.model.Leave;
import custom.clinic.model.dto.LeaveForm;
import custom.clinic.service.LeaveService;
import custom.clinic.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DefaultLeaveService implements LeaveService {

    @Resource
    private LeaveDao leaveDao;
    @Resource
    private UserService userService;

    @Override
    public void save(Leave leave) {
        leaveDao.save(leave);
    }

    @Override
    public Leave createLeaveFromDto(LeaveForm leaveDto) {
        return Leave.builder()
                .begin(leaveDto.getStartLeave())
                .end(leaveDto.getEndLeave())
                .doctor(userService.getCurrentUser().getDoctor())
                .isActive(false)
                .build();
    }

    @Override
    public List<Leave> getAllLeaves() {
        return leaveDao.findAll();
    }

    @Override
    public Leave getLeaveById(int id) {
        return leaveDao.getOne(id);
    }

    @Override
    public void changeLeaveStatus(int id, boolean status) {
        Leave leave = getLeaveById(id);
        leave.setActive(status);
        leaveDao.save(leave);
    }
}
