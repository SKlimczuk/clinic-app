package custom.clinic.service;

import custom.clinic.model.Leave;
import custom.clinic.model.dto.LeaveForm;

public interface LeaveService {

    void save(Leave leave);

    Leave createLeaveFromDto(LeaveForm leaveDto);
}
