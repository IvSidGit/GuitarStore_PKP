package org.example.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.AuthorityUtils;

import static org.assertj.core.api.Assertions.assertThat;

class SessionSecurityConfigTest {
    private final SessionSecurityConfig configuration = new SessionSecurityConfig();

    @Test
    void passwordsAreHashedAndEditorGetsBothRoles() {
        var encoder = configuration.passwordEncoder();
        var users = configuration.users(encoder);
        var editor = users.loadUserByUsername("editor");

        assertThat(editor.getPassword()).isNotEqualTo("editor");
        assertThat(encoder.matches("editor", editor.getPassword())).isTrue();
        assertThat(AuthorityUtils.authorityListToSet(editor.getAuthorities()))
                .containsExactlyInAnyOrder("ROLE_READER", "ROLE_EDITOR");
    }
}

