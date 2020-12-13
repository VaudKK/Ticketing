//package com.sda.ticketing.security;
//
//
//import com.sda.ticketing.models.User;
//import com.sda.ticketing.services.UserService;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsPasswordService;
//import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.Collection;
//
//@Component
//public class ReactiveOAuth2UserService implements ReactiveUserDetailsService, ReactiveUserDetailsPasswordService {
//
//    private final UserService userService;
//
//    public ReactiveOAuth2UserService(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public Mono<UserDetails> findByUsername(String username) {
//        return userService.getUser(username)
//                .map(CustomUser::new);
//    }
//
//    @Override
//    public Mono<UserDetails> updatePassword(UserDetails user, String newPassword) {
//        return userService.getUser(user.getUsername())
//                .doOnSuccess(u -> u.setPassword(newPassword))
//                .flatMap(this.userService::directSave)
//                .map(CustomUser::new);
//    }
//
//    private class CustomUser extends User implements UserDetails {
//
//        public CustomUser(User user) {
//            super(user);
//        }
//
//        @Override
//        public Collection<? extends GrantedAuthority> getAuthorities() {
//            return AuthorityUtils.createAuthorityList("ROLE_USER");
//        }
//
//        @Override
//        public String getUsername() {
//            return getPhoneNumber();
//        }
//
//        @Override
//        public boolean isAccountNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isAccountNonLocked() {
//            return true;
//        }
//
//        @Override
//        public boolean isCredentialsNonExpired() {
//            return true;
//        }
//
//        @Override
//        public boolean isEnabled() {
//            return true;
//        }
//    }
//}
