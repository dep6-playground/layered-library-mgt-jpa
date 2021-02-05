package lk.ijse.dep.web.lms.api;

import lk.ijse.dep.web.lms.business.BOFactory;
import lk.ijse.dep.web.lms.business.BOTypes;
import lk.ijse.dep.web.lms.business.custom.MemberBO;
import lk.ijse.dep.web.lms.dto.MemberDTO;

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

@WebServlet(name = "MemberServlet", value = "/api/v1/members/*")
public class MemberServlet extends HttpServlet {
    final Logger logger = LoggerFactory.getLogger(BookServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            super.service(req, resp);
        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{

            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid member id", null);
            }

            String id = req.getPathInfo().replace("/", "");

            MemberBO memberBO = BOFactory.getInstance().getBO(BOTypes.MEMBER);
            memberBO.setEntityManager(em);
            memberBO.deleteMember(id);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{

            if (req.getPathInfo() == null || req.getPathInfo().replace("/", "").trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid member id", null);
            }

            String id = req.getPathInfo().replace("/", "");
            Jsonb jsonb = JsonbBuilder.create();
            MemberDTO dto = jsonb.fromJson(req.getReader(), MemberDTO.class);

            if (dto.getId() != null || dto.getGender() == null  || dto.getName().toString().isEmpty()  || dto.getContact().trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid details", null);
            }

            MemberBO memberBO = BOFactory.getInstance().getBO(BOTypes.MEMBER);
            memberBO.setEntityManager(em);
            memberBO.updateMember(dto);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);

        } catch (JsonbException exp) {
            throw new HttpResponseException(400, "Failed to read the JSON", exp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            em.close();
        }    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();

        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{
            response.setContentType("application/json");
            MemberBO memberBO = BOFactory.getInstance().getBO(BOTypes.MEMBER);
            memberBO.setEntityManager(em);
            response.getWriter().println(jsonb.toJson(memberBO.findAllMembers()));

        } catch (Throwable t) {
            ResponseExceptionUtil.handle(t, response);
        } finally {
            em.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Jsonb jsonb = JsonbBuilder.create();
        final EntityManagerFactory emf = (EntityManagerFactory) getServletContext().getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try{
            MemberDTO dto = jsonb.fromJson(request.getReader(), MemberDTO.class);

            if (dto.getId() == null || dto.getGender() == null  || dto.getName().toString().isEmpty()  || dto.getContact().trim().isEmpty()) {
                throw new HttpResponseException(400, "Invalid details", null);
            }

            MemberBO memberBO = BOFactory.getInstance().getBO(BOTypes.MEMBER);
            memberBO.setEntityManager(em);
            memberBO.saveMember(dto);
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
