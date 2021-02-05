package lk.ijse.dep.web.lms.business.custom;

import lk.ijse.dep.web.lms.business.SuperBO;
import lk.ijse.dep.web.lms.dto.BorrowalDTO;
import lk.ijse.dep.web.lms.entity.BorrowalPK;

import java.util.List;

public interface BorrowalBO extends SuperBO {
    public void saveBorrowal(BorrowalDTO dto) throws Exception;

    public void updateBorrowal(BorrowalDTO dto) throws Exception;

    public void deleteBorrowal(BorrowalPK borrowalPK) throws Exception;

    public List<BorrowalDTO> findAllBorrowals() throws Exception;

}
