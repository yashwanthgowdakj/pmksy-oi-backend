package gov.kar.pmksy.service.impl;

import gov.kar.pmksy.dto.auth.LoginRequestDto;
import gov.kar.pmksy.dto.auth.LoginResponseDto;
import gov.kar.pmksy.entity.MstUser;
import gov.kar.pmksy.repository.UserRepository;
import gov.kar.pmksy.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDto login(
            LoginRequestDto request) {

        MstUser user =
                userRepository.findByUsername(
                                request.getUsername())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid Username"));

        if (!user.getPassword()
                .equals(request.getPassword())) {

            throw new RuntimeException(
                    "Invalid Password");
        }

        return LoginResponseDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .roleCode(user.getRole().getRoleCode())
                .roleName(user.getRole().getRoleName())
                .districtName(user.getDistrict() != null
                        ? user.getDistrict().getDistrictName()
                        : null)
                .talukName(user.getTaluk() != null
                        ? user.getTaluk().getTalukName()
                        : null)
                .build();
    }
}