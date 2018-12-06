package org.beny.ama.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.beny.ama.model.UserContext;
import org.beny.ama.util.AmaException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

public abstract class BaseController {

    private final Logger logger;
    private final MessageSource messageSource;
    private boolean listView;
    final String viewName;
    final String url;
    final String redirect = "redirect:/";

    public BaseController(String viewName, String url, MessageSource messageSource) {
        this.logger = LogManager.getLogger(this.getClass());
        this.messageSource = messageSource;
        this.viewName = viewName;
        this.url = url;
    }

    public BaseController(String viewName, String url, MessageSource messageSource, boolean listView) {
        this(viewName, url, messageSource);
        this.listView = listView;
    }

    @ExceptionHandler(AmaException.class)
    public RedirectView amaException(AmaException ex, RedirectAttributes attributes) {
        logger.warn(ex.getMessage());
        attributes.addFlashAttribute("info", ex.toString());
        return new RedirectView(url);
    }

    @ExceptionHandler(BindException.class)
    public RedirectView validationException(BindException ex, RedirectAttributes attributes) {
        attributes.addFlashAttribute("info", ex.getMessage());
        return new RedirectView(url);
    }

    @ExceptionHandler(Exception.class)
    public RedirectView exception(Exception ex, RedirectAttributes attributes) {
        logger.warn(ex.getMessage());
        attributes.addFlashAttribute("info", ex.getMessage());
        return new RedirectView(listView ? "/" : url);
    }

    String responseInfo(String viewName, Model model, String source) {
        model.addAttribute("info", messageSource.getMessage(source, null, LocaleContextHolder.getLocale()));
        return viewName;
    }

    String viewOrForwardToHome(String viewName) {
        return isAuthenticated() ? redirect : viewName;
    }

    String redirectToUrl() {
        return redirect+viewName;
    }

    UserContext getUserContext() {
        return isAuthenticated() ? (UserContext) SecurityContextHolder.getContext().getAuthentication().getPrincipal() : new UserContext();
    }

    boolean isAuthenticated() {
        return !(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken);
    }
}
