package lk.ijse.dep.web.lms.business.util;

import lk.ijse.dep.web.lms.dto.BookDTO;
import lk.ijse.dep.web.lms.dto.BorrowalDTO;
import lk.ijse.dep.web.lms.dto.MemberDTO;

import lk.ijse.dep.web.lms.entity.Book;
import lk.ijse.dep.web.lms.entity.Borrowal;
import lk.ijse.dep.web.lms.entity.BorrowalPK;
import lk.ijse.dep.web.lms.entity.Member;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EntityDTOMapper {

    EntityDTOMapper instance = Mappers.getMapper(EntityDTOMapper.class);

    @Mapping(target = "member2Book",ignore = true)
    Member getMember(MemberDTO dto);

    MemberDTO getMemberDTO(Member member);

    List<MemberDTO> getMemberDTOs(List<Member> students);

    @Mapping(target = "book2Member",ignore = true)
    Book getBook(BookDTO dto);

    BookDTO getBookDTO(Book book);

    List<BookDTO> getBookDTOs(List<Book> customers);

    @Mapping(target = "member",ignore = true)
    @Mapping(target = "book",ignore = true)
    @Mapping(source = ".",target = "borrowalPK")
    Borrowal getBorrowal(BorrowalDTO dto);

    default BorrowalPK getBorrowalPK(BorrowalDTO dto){
        return new BorrowalPK(dto.getMemberId(),dto.getBookId());
    }

    BorrowalDTO getBorrowalDTO(Borrowal borrowal);

    List<BorrowalDTO> getBorrowalDTOs(List<Borrowal> borrowals);

}
