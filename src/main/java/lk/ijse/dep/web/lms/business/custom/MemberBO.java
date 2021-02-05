package lk.ijse.dep.web.lms.business.custom;

import lk.ijse.dep.web.lms.business.SuperBO;
import lk.ijse.dep.web.lms.dto.MemberDTO;

import java.util.List;

public interface MemberBO extends SuperBO {

    public void saveMember(MemberDTO dto) throws Exception;

    public void updateMember(MemberDTO dto) throws Exception;

    public void deleteMember(String memberId) throws Exception;

    public List<MemberDTO> findAllMembers() throws Exception;
}
