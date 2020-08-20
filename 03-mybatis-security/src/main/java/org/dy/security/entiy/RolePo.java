package org.dy.security.entiy;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author caiwl
 * @date 2020/8/20 17:09
 */
@Data
public class RolePo implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    private String username;
    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
