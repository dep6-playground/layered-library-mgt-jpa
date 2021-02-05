package lk.ijse.dep.web.lms.dao;

import lk.ijse.dep.web.lms.dao.custom.impl.BookDAOImpl;
import lk.ijse.dep.web.lms.dao.custom.impl.BorrowalDAOImpl;
import lk.ijse.dep.web.lms.dao.custom.impl.MemberDAOImpl;
import lk.ijse.dep.web.lms.dao.custom.impl.QueryDAOImpl;

public class DAOFactory {

    private static DAOFactory daoFactory;

    private DAOFactory(){

    }

    public static DAOFactory getInstance(){
        return (daoFactory != null)? daoFactory: (daoFactory = new DAOFactory());
    }

    public <T extends SuperDAO> T getDAO(DAOTypes daoType){
        switch (daoType){
            case MEMBER:
                return (T) new MemberDAOImpl();
            case BOOK:
                return (T) new BookDAOImpl();
            case BORROWAL:
                return (T) new BorrowalDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
            default:
                return null;
        }
    }
}
