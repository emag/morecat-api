package org.emamotor.morecat.admin.user;

import lombok.Getter;
import lombok.Setter;
import org.emamotor.morecat.model.Role;
import org.emamotor.morecat.model.User;
import org.emamotor.morecat.service.RoleService;
import org.emamotor.morecat.service.UserService;
import org.slf4j.Logger;

import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
@Named
@ViewScoped
public class UserEditController implements Serializable {

    @Inject
    private FacesContext facesContext;

    @Inject
    private Logger logger;

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Getter
    @Setter
    private User user = new User();

    public void doFind() {

        // new user
        if (user.getId() == null
                || userService.findById(user.getId()) == null) {
            return;
        }

        // existing user
        this.user = userService.findById(user.getId());

    }

    public String doSave() {
        userService.update(this.user);
        return "view?faces-redirect=true";
    }

    public String getRole_() {
        return this.user.getRole().name();
    }

    public void setRole_(String selectedRole) {
        this.user.setRole(Role.valueOf(selectedRole));
    }

}
