package lk.ijse.dep.web.lms.api;

import lk.ijse.dep.web.lms.business.BOFactory;
import lk.ijse.dep.web.lms.business.BOTypes;
import lk.ijse.dep.web.lms.business.custom.RegisterBO;
import lk.ijse.dep.web.lms.dto.RegisterDTO;
import lk.ijse.dep.web.lms.exception.HttpResponseException;
import lk.ijse.dep.web.lms.exception.ResponseExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

@WebServlet(name = "BorrowalServlet", value = "/api/v1/registers/*")
public class BorrowalServlet extends HttpServlet {
    final Logger logger = LoggerFactory.getLogger(BorrowalServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{
            RegisterDTO dto = jsonb.fromJson(request.getReader(), RegisterDTO.class);

            if (dto.getStudentId() == null || dto.getCourseCode() ==null || dto.getRegisterFee()==null || dto.getRegisteredDate() ==null) {
                throw new HttpResponseException(400, "Invalid details", null);
            }

            RegisterBO registerBO = BOFactory.getInstance().getBO(BOTypes.REGISTER);
            registerBO.setEntityManager(em);
            registerBO.saveRegister(dto);
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.setContentType("application/json");
            response.getWriter().println(jsonb.toJson(dto));
        } catch (SQLIntegrityConstraintViolationException exp) {
            throw new HttpResponseException(400, "Duplicate entry", exp);
        } catch (JsonbException exp) {
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            em.close();
        }
    }
}
