package fr.isika.cda.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import fr.isika.cda.entities.User;

/**
 * Utility class that manages a session for users.
 */
public final class SessionUtils {

	private static final String CONNECTED_ACCOUNT_ID = "connectedAccountId";
	private static final String CONNECTED_USER = "connectedUser";

	/*
	 * Prevent from instantiating this utility class
	 */
	private SessionUtils() {
	}

	/*
	 * specific methods
	 */
	public static boolean isUserConnected() {
		return getConnectedAccountId() != null;
	}

	/*
	 * setters and getters
	 */
	public static void setConnectedAccountId(Long connectedAccountId) {
		HttpSession session = getSession();
		session.setAttribute(CONNECTED_ACCOUNT_ID, connectedAccountId);
	}

	public static Long getConnectedAccountId() {
		HttpSession session = getSession();
		return (Long) session.getAttribute(CONNECTED_ACCOUNT_ID);
	}

	public static void setConnectedUser(User user) {
		HttpSession session = getSession();
		session.setAttribute(CONNECTED_USER, user);
	}

	public static User getConnectedUser() {
		HttpSession session = getSession();
		return (User) session.getAttribute(CONNECTED_USER);
	}

	private static HttpSession getSession() {
		return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	public static void resetSession() {
		HttpSession session = getSession();
		session.invalidate();
	}

}
