package custom.clinic.service.impl;

import custom.clinic.dao.PatientDao;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private PatientDao patientDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getPatientDao().findPatientByEmail(username);
    }

    private PatientDao getPatientDao() {
        return patientDao;
    }
}
