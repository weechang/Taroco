package xyz.weechang.taroco.core.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import xyz.weechang.taroco.core.model.AuditEntry;

/**
 * 说明：
 *
 * @author zhangwei
 * @version 2017/11/19 23:13.
 */
public class BaseController {

    protected String getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return SecurityContextHolder.getContext().getAuthentication().getName();
        }
        return null;
    }

    protected AuditEntry createAudit() {
        return new AuditEntry(getCurrentUser());
    }
}
