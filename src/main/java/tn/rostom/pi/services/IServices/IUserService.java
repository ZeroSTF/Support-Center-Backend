package tn.rostom.pi.services.IServices;

import tn.rostom.pi.entities.User;

public interface IUserService {
    User getCurrentUser();

    String getRoleString(User user);
}
