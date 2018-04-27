package by.epam.ivanov.aviacompany.controller;

public class Forward {
    private String page;
    private boolean redirect;

    public Forward(String page, boolean redirect) {
        this.page = page;
        this.redirect = redirect;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public boolean isRedirect() {
        return redirect;
    }

    public void setRedirect(boolean redirect) {
        this.redirect = redirect;
    }
}
