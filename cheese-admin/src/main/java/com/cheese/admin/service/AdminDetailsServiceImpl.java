package com.cheese.admin.service;



import com.cheese.domain.domain.admin.Admin;
import com.cheese.domain.domain.admin.AdminRepository;
import com.cheese.domain.domain.adminRole.AdminRole;
import com.cheese.domain.domain.adminRole.AdminRoleRepository;
import com.cheese.domain.domain.enums.EAdminRole;
import com.cheese.domain.dto.request.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AdminDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    AdminRoleRepository adminRoleRepository;

    @Transactional
    @Override
    public AdminDetailsImpl loadUserByUsername(String username) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin " + username + " can not be found");
        }

        return AdminDetailsImpl.build(admin);
    }

    @Transactional
    public String save(SignupRequest payload) throws UsernameNotFoundException {
        if (adminRepository.existsByEmail(payload.getEmail()))
            System.out.println("hi");
            //            throw new CustomException(CustomExceptionCode.EMAIL_IS_EXIST);

        if (adminRepository.existsByUsername(payload.getUsername()))
            System.out.println("hi");
//            throw new CustomException(CustomExceptionCode.USERNAME_IS_EXIST);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Admin user = Admin.builder()
                .email(payload.getEmail())
                .username(payload.getUsername())
                .password(encoder.encode(payload.getPassword()))
                .build();

        adminRepository.save(user);

        Set<String> strRoles = payload.getRoles();
        Set<AdminRole> roles = new HashSet<>();
        if (strRoles == null){
            AdminRole userRole = adminRoleRepository.findByName(EAdminRole.ROLE_ADMIN_READ).get();

            if(userRole==null)
                System.out.println("hi");
//            throw new CustomException(CustomExceptionCode.REQUEST_ROLE_NOT_FOUND);

            roles.add(userRole);

        } else {
            for (String role: strRoles){
                switch (role) {
                    case "ADMIN":
                        AdminRole adminRole = adminRoleRepository.findByName(EAdminRole.ROLE_ADMIN).get();

                        if(adminRole==null){
                            System.out.println("hi");
//                            throw new CustomException(CustomExceptionCode.REQUEST_ROLE_NOT_FOUND);
                        }
                        roles.add(adminRole);

                        break;
                    case "READ":
                        AdminRole managerRole = adminRoleRepository.findByName(EAdminRole.ROLE_ADMIN_READ).get();

                        if(managerRole==null){
                            System.out.println("hi");
//                            throw new CustomException(CustomExceptionCode.REQUEST_ROLE_NOT_FOUND);
                        }
                        roles.add(managerRole);

                        break;
                    default:
                        AdminRole userRole = adminRoleRepository.findByName(EAdminRole.ROLE_ADMIN_CUSTOM).get();

                        if(userRole==null){
                            System.out.println("hi");
//                            throw new CustomException(CustomExceptionCode.REQUEST_ROLE_NOT_FOUND);
                        }

                        roles.add(userRole);

                }
            }
        }

        user.setAdminRoles(roles);
        adminRepository.save(user);
        return "ok";
//        return new BaseResponse(true, "OK");

    }
}
