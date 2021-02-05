package lk.ijse.dep.web.lms.business.custom;

import lk.ijse.dep.web.lms.business.SuperBO;
import lk.ijse.dep.web.lms.dto.BookDTO;

import java.util.List;

public interface BookBO extends SuperBO {

    public void saveBook(BookDTO dto) throws Exception;

    public void updateBook(BookDTO dto) throws Exception;

    public void deleteBook(String bookId) throws Exception;

    public List<BookDTO> findAllBooks() throws Exception;
}
