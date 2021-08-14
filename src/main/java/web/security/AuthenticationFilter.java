//package web.security;
//
//import web.model.User;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//import static java.util.Objects.nonNull;
//
//public class AuthenticationFilter implements Filter {
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//
//        String login = request.getParameter("login");
//        String password = request.getParameter("password");
//
//        //UserDaoImpl userDao = new UserDaoImpl();
//
//        HttpSession session = request.getSession();
//
//        if (nonNull(session) && nonNull(session.getAttribute("login")) && nonNull(session.getAttribute("password"))) {
//            User.ROLE role = (User.ROLE) session.getAttribute("role");
//
//            moveToMenu(request, response, role);
//        } else if (userDao.userIsExist(login, password)) {
//
//            User.ROLE role = userDao.getRoleByLoginPassword(login, password);
//
//            request.getSession().setAttribute("password", password);
//            request.getSession().setAttribute("login", login);
//            request.getSession().setAttribute("role", role);
//
//            moveToMenu(request, response, role);
//        } else {
//            moveToMenu(request, response, User.ROLE.UNKNOWN);
//        }
//    }
//
//    private void moveToMenu(HttpServletRequest req, HttpServletResponse res, User.ROLE role) throws ServletException, IOException {
//        if (role.equals(User.ROLE.ADMIN)) {
//            req.getRequestDispatcher("/WEB-INF/view/adminPage.jsp").forward(req, res);
//        } else if (role.equals(User.ROLE.USER)) {
//            req.getRequestDispatcher("/WEB-INF/view/userPage.jsp").forward(req, res);
//        } else {
//            req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, res);
//        }
//    }
//}
