package lk.ijse.dep.web.lms.business;


import lk.ijse.dep.web.lms.business.custom.impl.BookBOImpl;
import lk.ijse.dep.web.lms.business.custom.impl.BorrowalBOImpl;
import lk.ijse.dep.web.lms.business.custom.impl.MemberBOImpl;

public class BOFactory {

    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance() {
        return (boFactory != null) ? boFactory : (boFactory = new BOFactory());
    }

    public <T extends SuperBO> T getBO(BOTypes boType) {
        switch (boType) {
            case MEMBER:
                return (T) new MemberBOImpl();
            case BOOK:
                return (T) new BookBOImpl();
            case BORROWAL:
                return (T) new BorrowalBOImpl();
            default:
                return null;
        }
    }
}
