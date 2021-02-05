package lk.ijse.dep.web.lms.business.custom.impl;

import lk.ijse.dep.web.lms.business.custom.MemberBO;
import lk.ijse.dep.web.lms.business.util.EntityDTOMapper;
import lk.ijse.dep.web.lms.dao.DAOFactory;
import lk.ijse.dep.web.lms.dao.DAOTypes;
import lk.ijse.dep.web.lms.dao.custom.MemberDAO;
import lk.ijse.dep.web.lms.dto.MemberDTO;


import javax.persistence.EntityManager;
import java.util.List;

public class MemberBOImpl implements MemberBO {

    private final MemberDAO memberDAO;
    private EntityManager em;
    private final EntityDTOMapper mapper = EntityDTOMapper.instance;

    public MemberBOImpl() {
        memberDAO = DAOFactory.getInstance().getDAO(DAOTypes.MEMBER);
    }

    @Override
    public void setEntityManager(EntityManager em) {
        this.em = em;
        memberDAO.setEntityManager(em);

    }

    @Override
    public void saveMember(MemberDTO dto) throws Exception {
        try {
            em.getTransaction().begin();
            memberDAO.save(mapper.getMember(dto));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public void updateMember(MemberDTO dto) throws Exception {
        try {
            em.getTransaction().begin();
            memberDAO.update(mapper.getMember(dto));
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public void deleteMember(String memberId) throws Exception {
        try {
            em.getTransaction().begin();
            memberDAO.delete(memberId);
            em.getTransaction().commit();
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }

    }

    @Override
    public List<MemberDTO> findAllMembers() throws Exception {
        try {
            em.getTransaction().begin();
            List<MemberDTO> memberDTOs = mapper.getMemberDTOs(memberDAO.getAll());
            em.getTransaction().commit();
            return memberDTOs;
        } catch (Throwable t) {
            em.getTransaction().rollback();
            throw t;
        }
    }
}
