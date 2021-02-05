package lk.ijse.dep.web.lms.business.custom.impl;

import lk.ijse.dep.web.lms.business.custom.BookBO;
import lk.ijse.dep.web.lms.business.util.EntityDTOMapper;
import lk.ijse.dep.web.lms.dao.DAOFactory;
import lk.ijse.dep.web.lms.dao.DAOTypes;
import lk.ijse.dep.web.lms.dao.custom.BookDAO;
import lk.ijse.dep.web.lms.dto.BookDTO;

import javax.persistence.EntityManager;
import java.util.List;

public class BookBOImpl implements BookBO {

    private final BookDAO bookDAO;
    private EntityManager em;
    private final EntityDTOMapper mapper = EntityDTOMapper.instance;

    public BookBOImpl() {
        bookDAO = DAOFactory.getInstance().getDAO(DAOTypes.BOOK);
    }


    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
        bookDAO.setEntityManager(em);
    }

    @Override
    public void saveBook(BookDTO dto) throws Exception {
        try {
            em.getTransaction().begin();
            bookDAO.save(mapper.getBook(dto));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public void updateBook(BookDTO dto) throws Exception {
        try {
            em.getTransaction().begin();
            bookDAO.update(mapper.getBook(dto));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public void deleteBook(String bookId) throws Exception {
        try {
            em.getTransaction().begin();
            bookDAO.delete(bookId);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public List<BookDTO> findAllBooks() throws Exception {
        try {
            em.getTransaction().begin();
            List<BookDTO> bookDTOs = mapper.getBookDTOs(bookDAO.getAll());
            em.getTransaction().commit();
            return bookDTOs;
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }    }
}
