package servlet;

import be.hbo5.java.menu.MenuItem;
import be.hbo5.java.menu.MenuLink;
import be.hbo5.java.menu.MenuList;
import controller.AuthBean;
import controller.NavBarBean;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * A Filter for every request that checks whether the user can access it.
 * https://stackoverflow.com/tags/servlet-filters/info
 *
 * @author woutbr@student.hik.be
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"})
public class LoginFilter implements Filter {

    @Inject
    private AuthBean authBean;
    @Inject
    private NavBarBean navBarBean;

    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig filterConfig = null;

    private static final String AJAX_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"%s\"></redirect></partial-response>";
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("", "/index.xhtml", "/login.xhtml", "/logout")));

    /**
     * How implement a login filter in JSF? : answer BalusC
     * https://stackoverflow.com/a/8480241
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String loginURL = request.getContextPath() + "/login.xhtml";

        if (this.isRequestAllowed(request, loginURL)) {
            if (!isResourceRequest(request)) { // Prevent browser from caching restricted resources. See also https://stackoverflow.com/q/4194207/157882
                response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
                response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
                response.setDateHeader("Expires", 0); // Proxies.
            }

            chain.doFilter(request, response); // So, just continue request.

        } else if (isAjaxRequest(request)) {
            response.setContentType("text/xml");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().printf(AJAX_REDIRECT_XML, loginURL); // So, return special XML response instructing JSF ajax to send a redirect.
        } else {

            response.sendRedirect(loginURL); // So, just perform standard synchronous redirect.

        }
    }

    private boolean isRequestAllowed(HttpServletRequest request, String loginURL) {
        boolean debugMode = this.authBean.isDebugMode();
        boolean loginRequest = request.getRequestURI().equals(loginURL);

        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);
        boolean loggedIn = this.authBean.isLoggedIn();
        boolean roleAllowed = isRoleAllowed(request, path);
        System.out.println("LoginFilter: "+path);

        return debugMode || loginRequest || isResourceRequest(request) || allowedPath || loggedIn && roleAllowed;
    }

    private boolean isRoleAllowed(HttpServletRequest request, String path) {
        MenuList items = this.navBarBean.getLinks();
        //TODO Create list of links without needing to walk it everytime.
        MenuLink link = walkUntilFound(items, request, path);
        return link != null && this.authBean.hasAtLeastOneRole(link.getRoles());
    }
    
    /**
     * Search for a MenuLink with an outcome equal to the current request path.
     * @return null if no MenuLink has been found.
     */
    private MenuLink walkUntilFound(MenuList items, HttpServletRequest request, String path) {
        MenuLink link = null;
        for (MenuItem mi : items) {
            if (MenuLink.class.isInstance(mi)) {
                String completePath = request.getContextPath() + "/" +((MenuLink) mi).getOutcome();
                if(completePath.equals(path)){
                    return (MenuLink) mi;
                }
            } else if (MenuList.class.isInstance(mi)) {
                link = walkUntilFound((MenuList) mi, request, path);
                if(link != null){
                    return link;
                }
            }
        }
        return link;
    }

    private boolean isResourceRequest(HttpServletRequest request) {
        return request.getRequestURI().startsWith(request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER + "/");
    }

    private boolean isAjaxRequest(HttpServletRequest request) {
        return "partial/ajax".equals(request.getHeader("Faces-Request"));
    }

    @Override
    public void destroy() {
        // If you have assigned any expensive resources as field of
        // this Filter class, then you could clean/close them here.
    }

    /**
     * Return the filter configuration object for this filter.
     */
    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    /**
     * Set the filter configuration object for this filter.
     *
     * @param filterConfig The filter configuration object
     */
    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    /**
     * Init method for this filter
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
        if (filterConfig != null) {
            log("LoginFilter:Initializing filter");
        }
    }

    /**
     * Return a String representation of this object.
     */
    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("LoginFilter()");
        }
        StringBuilder sb = new StringBuilder("LoginFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }

    public void log(String msg) {
        filterConfig.getServletContext().log(msg);
    }

}
