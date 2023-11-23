package com.springframework.bookdelivery.service.impl;

import com.springframework.bookdelivery.dto.UserDTO;
import com.springframework.bookdelivery.entity.User;
import com.springframework.bookdelivery.enums.Role;
import com.springframework.bookdelivery.exception.user.EmailAlreadyExistsException;
import com.springframework.bookdelivery.mapper.UserMapper;
import com.springframework.bookdelivery.payload.request.customer.CustomerCreateRequest;
import com.springframework.bookdelivery.repository.UserRepository;
import com.springframework.bookdelivery.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    /**
     * Creates a new customer based on the provided customer creation request.
     *
     * @param request The request containing customer information to be used for creation.
     * @return A {@link User} representing the newly created customer.
     */
    @Override
    public UserDTO createCustomer(CustomerCreateRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = User.builder()
                .email(request.getEmail())
                .fullName(request.getFullName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();

        return UserMapper.toDTO(userRepository.save(user));

    }

}
