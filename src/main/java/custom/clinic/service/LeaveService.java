package custom.clinic.service;

import custom.clinic.model.Leave;
import custom.clinic.model.dto.LeaveForm;

import java.util.List;

public interface LeaveService {

    void save(Leave leave);

    Leave createLeaveFromDto(LeaveForm leaveDto);

    List<Leave> getAllLeaves();

    Leave getLeaveById(int id);

    void changeLeaveStatus(int id, boolean status);
}
