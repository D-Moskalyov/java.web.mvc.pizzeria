package web.pizzeria.service;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import web.pizzeria.model.Category;
import web.pizzeria.model.Good;

@Repository(value = "goodDAO")
@Transactional
//@Configuration
public class GoodDAO implements GoodService {

    @Autowired
    @Qualifier(value = "sessionFactory")
    SessionFactory sf;
    @Override
    public List<Good> getList(Integer id) {
        Query q = sf.getCurrentSession().createQuery("from Good g where g.category.id = :catId");
        q.setInteger("catId", id);
        return q.list();
    }

    @Override
    public List<Category> getCategoryList() {
        Query q = sf.getCurrentSession().createQuery("from Category");
        return q.list();
    }

    @Override
    public Good find(Integer id) {
        return (Good) sf.getCurrentSession().get(Good.class, id);
    }

    @Override
    public List<Good> getToppings() {
        String query = "select from Goods where category='2'";
        return sf.getCurrentSession().createQuery(query).list();
    }

    @Override
    public Category findCategory(Integer id) {
        return (Category) sf.getCurrentSession().get(Category.class, id);
    }


}
