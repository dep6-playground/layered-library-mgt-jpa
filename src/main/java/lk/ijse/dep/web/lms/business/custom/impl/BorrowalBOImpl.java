package lk.ijse.dep.web.lms.business.custom.impl;

import lk.ijse.dep.web.lms.business.custom.BorrowalBO;
import lk.ijse.dep.web.lms.business.util.EntityDTOMapper;
import lk.ijse.dep.web.lms.dao.DAOFactory;
import lk.ijse.dep.web.lms.dao.DAOTypes;
import lk.ijse.dep.web.lms.dao.custom.BorrowalDAO;
import lk.ijse.dep.web.lms.dto.BorrowalDTO;
import lk.ijse.dep.web.lms.entity.BorrowalPK;


import javax.persistence.EntityManager;
import java.util.List;

public class BorrowalBOImpl implements BorrowalBO {
    private final BorrowalDAO borrowalDAO;
    private EntityManager em;
    private final EntityDTOMapper mapper = EntityDTOMapper.instance;

    public BorrowalBOImpl() {

        borrowalDAO = DAOFactory.getInstance().getDAO(DAOTypes.BORROWAL);
    }


    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
        borrowalDAO.setEntityManager(em);
    }

    @Override
    public void saveBorrowal(BorrowalDTO dto) throws Exception {
        try {
            em.getTransaction().begin();
            borrowalDAO.save(mapper.getBorrowal(dto));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public void updateBorrowal(BorrowalDTO dto) throws Exception {
        try {
            em.getTransaction().begin();
            borrowalDAO.update(mapper.getBorrowal(dto));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public void deleteBorrowal(BorrowalPK borrowalPK) throws Exception {
        try {
            em.getTransaction().begin();
            borrowalDAO.delete(borrowalPK);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public List<BorrowalDTO> findAllBorrowals() throws Exception {
        try {
            em.getTransaction().begin();
            List<BorrowalDTO> borrowalDTOs = mapper.getBorrowalDTOs(borrowalDAO.getAll());
            em.getTransaction().commit();
            return borrowalDTOs;
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }
    }
}
