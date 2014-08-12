///**
// *
// *JOJO
// */
//package com.jojo.web.common.authenticate;
//
//import java.io.IOException;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import org.apache.commons.lang.StringUtils;
//import org.dom4j.Document;
//import org.dom4j.DocumentException;
//import org.dom4j.Element;
//import org.dom4j.io.SAXReader;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.core.io.Resource;
//
///**
// *
// * @author JOJO
// */
//public class AuthenticationConfigReader {
//
//    private static final Logger logger             = LoggerFactory
//                                                       .getLogger(AuthenticationConfigReader.class);
//
//    private static final String NEED_LOGIN_ATTR    = "needLogin";
//    private static final String ROLES_ATTR         = "roles";
//    private static final String ROLES_EL           = ROLES_ATTR;
//    private static final String AUTHENTICATIONS_EL = "authentications";
//    private static final String AUTHENTICATION_EL  = "authentication";
//    private static final String URI_PREFIX_ATTR    = "uriPrefix";
//    private static final String URI_ATTR           = "uri";
//
//    @SuppressWarnings("unchecked")
//    public void loadAuthenticationDefinition(Resource resource) {
//        SAXReader reader = new SAXReader();
//        Document doc = null;
//        try {
//            doc = reader.read(resource.getInputStream());
//            final Element rootEl = doc.getRootElement();
//            final String globalLoginFlag = StringUtils.trimToEmpty(rootEl
//                .attributeValue(NEED_LOGIN_ATTR));
//
//            final String globalRoles = StringUtils.trimToEmpty(rootEl.attributeValue(ROLES_ATTR));
//
//            // <authentications>
//            List<Element> authenticationsEls = rootEl.elements(AUTHENTICATIONS_EL);
//            for (Element authenticationsEl : authenticationsEls) {
//                String uriPrefix = StringUtils.trimToEmpty(authenticationsEl
//                    .attributeValue(URI_PREFIX_ATTR));
//                String parentLoginFlag = StringUtils.defaultIfEmpty(
//                    authenticationsEl.attributeValue(NEED_LOGIN_ATTR), globalLoginFlag);
//                String parentRoles = StringUtils.defaultIfEmpty(
//                    authenticationsEl.elementText(ROLES_EL), globalRoles);
//
//                //<authentication>
//                List<Element> authenticationEls = authenticationsEl.elements(AUTHENTICATION_EL);
//                for (Element authenticationEl : authenticationEls) {
//                    processAuthenticationsEl(uriPrefix, parentLoginFlag, parentRoles,
//                        authenticationEl);
//                }
//            }
//        } catch (DocumentException e) {
//            logger.error(e.getMessage(), e);
//        } catch (IOException e) {
//            logger.error(e.getMessage(), e);
//        }
//    }
//
//    private void processAuthenticationsEl(String uriPrefix, String parentLoginFlag,
//                                          String parentRoles, Element authenticationEl) {
//        String loginFlag = StringUtils.defaultIfEmpty(
//            authenticationEl.attributeValue(NEED_LOGIN_ATTR), parentLoginFlag);
//
//        String roles = StringUtils.defaultIfEmpty(authenticationEl.elementText(ROLES_EL),
//            parentRoles);
//
//        String uri = (uriPrefix + StringUtils
//            .trimToEmpty(authenticationEl.attributeValue(URI_ATTR)));
//
//        AuthenticationDefinition authenticationDefinition = new AuthenticationDefinition();
//        authenticationDefinition.setNeedLogin("true".equalsIgnoreCase(loginFlag));
//        authenticationDefinition.setUri(uri);
//
//        Set<String> roleSet = new HashSet<String>();
//        String[] roleArr = roles.split(",");
//        for (String role : roleArr) {
//            roleSet.add(role);
//        }
//        authenticationDefinition.setRoles(roleSet);
//
//        AuthenticationDefinitionRegister.regist(uri, authenticationDefinition);
//    }
//}
