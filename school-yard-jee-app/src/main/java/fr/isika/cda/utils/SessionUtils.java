package fr.isika.cda.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.isika.cda.entities.school.Member;
import fr.isika.cda.entities.school.School;
import fr.isika.cda.entities.users.User;

/**
 * Utility class that manages a session for users.
 */
public final class SessionUtils {

// private static final String CONNECTED_ACCOUNT_ID = "connectedAccountId";
   private static final String CONNECTED_USER = "connectedUser";
   private static final String CURRENT_SCHOOL = "currentSchool";
   private static final String CONNECTED_MEMBER = "connectedMember";

   /*
    * Prevent from instantiating this utility class
    */
   private SessionUtils() {
   }

   /*
    * specific methods
    */
   public static boolean isUserConnected() {
      return getConnectedUser() != null;
   }
   public static boolean isSchoolCurrent() {
      return getCurrentSchool() != null;
   }
   public static boolean isMemberConnected() {
      return getConnectedMember() != null;
   }

   /*
    * setters and getters
    */
// public static void setConnectedAccountId(Long connectedAccountId) {
//    HttpSession session = getSession();
//    session.setAttribute(CONNECTED_ACCOUNT_ID, connectedAccountId);
// }
//
// public static Long getConnectedAccountId() {
//    HttpSession session = getSession();
//    return (Long) session.getAttribute(CONNECTED_ACCOUNT_ID);
// }

   public static void setConnectedUser(User user) {
      HttpSession session = getSession();
      session.setAttribute(CONNECTED_USER, user);
   }

   public static User getConnectedUser() {
      HttpSession session = getSession();
      return (User) session.getAttribute(CONNECTED_USER);
   }

   public static void setCurrentSchool(School school) {
      HttpSession session = getSession();
      session.setAttribute(CURRENT_SCHOOL, school);
   }

   public static void setConnectedMember(Member member) {
      HttpSession session = getSession();
      session.setAttribute(CONNECTED_MEMBER, member);
   }

   public static Member getConnectedMember() {
      HttpSession session = getSession();
      return (Member) session.getAttribute(CONNECTED_MEMBER);
   }

   public static School getCurrentSchool() {
      HttpSession session = getSession();
      return (School) session.getAttribute(CURRENT_SCHOOL);
   }

   private static HttpSession getSession() {
      return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
   }

   public static void resetSession() {
      HttpSession session = getSession();
      session.invalidate();
   }



}