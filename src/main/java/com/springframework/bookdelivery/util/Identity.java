package com.springframework.bookdelivery.util;

import com.springframework.bookdelivery.enums.BeanScope;
import com.springframework.bookdelivery.security.CustomUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@Scope(value = BeanScope.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@AllArgsConstructor
public class Identity {

    /**
     * Retrieve the custom user details of the currently authenticated user.
     *
     * @return The CustomUserDetails object containing user details.
     */
    public CustomUserDetails getCustomUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return (CustomUserDetails) userDetails;
    }
}
