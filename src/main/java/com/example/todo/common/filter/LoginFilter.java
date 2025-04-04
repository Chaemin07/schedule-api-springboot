package com.example.todo.common.filter;

//@Slf4j
//public class LoginFilter implements Filter {
//
////    private static final String[] WHITE_LIST = {"/auth*", "/css/*", "/js/*"};
//    private static final String[] WHITE_LIST = {"*"};
//
//
//    @Override
//    public void doFilter(
//            ServletRequest request,
//            ServletResponse response,
//            FilterChain chain
//    ) throws IOException, ServletException {
//
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        String requestURI = httpRequest.getRequestURI();
//
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//
//        log.info("로그인 필터 로직 실행");
//
//        if (!isWhiteList(requestURI)) {
//
//            HttpSession session = httpRequest.getSession(false);
//            if (!SessionManager.isLogin(session)) {
//                log.info("미인증 사용자 요청: {}", requestURI);
//                httpResponse.sendRedirect("/auth/session-home");
//            }
//            log.info("화이트리스트 경로 접근: {}", requestURI);
//
//            // 로그인 성공 로직
//            log.info("로그인에 성공했습니다.");
//        }
//        log.info("화이트리스트 경로 접근: {}", requestURI);
//        chain.doFilter(request, response);
//    }
//
//    private boolean isWhiteList(String requestURI) {
//        return PatternMatchUtils.simpleMatch(WHITE_LIST, requestURI);
//    }
//}
