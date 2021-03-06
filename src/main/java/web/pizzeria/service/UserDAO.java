package web.pizzeria.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import web.pizzeria.model.User;

@Repository(value = "userDAO")
@Transactional
//@Configuration
public class UserDAO implements UserService, UserDetailsService {
    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sf;

    @Override
    public List<User> findAll() throws SQLException {
        return sf.getCurrentSession().createQuery("from users").list();
    }

    @Override
    public User findFlowUser() throws CustomerNotIdentityException, SQLException {
        User activeUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return activeUser;
    }

    @Override
    public User findUser(User us) throws CustomerNotFoundException, SQLException {
        String query = "select from users where Email=" + us.getEmail() + " and password=" + us.getPassword();
        List<User> users = sf.getCurrentSession().createQuery(query).list();

        return users.get(0);
    }

    @Override
    public User attemptAddCustomer(User us) throws CustomerAlreadyExist, SQLException {
        String query = "select from users where Email=" + us.getEmail() + " and password=" + us.getPassword();
        List<User> users = sf.getCurrentSession().createQuery(query).list();

        return users.get(0);
    }

    @Override
    public int save(User u) {
        return  (Integer) sf.getCurrentSession().save(u);
    }

    @Override
    public User find(Integer id) {
        return (User) sf.getCurrentSession().get(User.class, id);
    }

    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        User user = null;

        try {
            Query q = sf.getCurrentSession().createQuery("from User u where u.email = :email");
            q.setString("email", string);
            List<User> users = q.list();
            if (users.isEmpty()) {
                throw new UsernameNotFoundException("User " + string + " not found");
            } else {
                return users.get(0);
            }
        } catch (Exception e) {
            System.err.println("ohshit");
        }
        return null;
    }
}
