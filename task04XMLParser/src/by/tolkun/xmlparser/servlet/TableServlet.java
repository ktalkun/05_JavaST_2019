package by.tolkun.xmlparser.servlet;

import by.tolkun.xmlparser.builder.dom.MedicinesDOMBuilder;
import by.tolkun.xmlparser.builder.jaxb.MedicinesJAXBBuilder;
import by.tolkun.xmlparser.builder.sax.MedicinesSAXBuilder;
import by.tolkun.xmlparser.builder.stax.MedicinesStAXBuilder;
import by.tolkun.xmlparser.entity.medicine.Medicine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.jsp.jstl.core.Config;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Class to handle http requests.
 *
 * @author Kirill Tolkun
 */
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 2)
public class TableServlet extends HttpServlet {

    /**
     * Logger of class {@code TableServlet}.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TableServlet.class);

    /**
     * Pattern of xml files.
     */
    private static final Pattern XML_FILE_PATTERN
            = Pattern.compile(".+\\.xml");

    /**
     * Pattern of xsd files.
     */
    private static final Pattern XSD_FILE_PATTERN
            = Pattern.compile(".+\\.xsd");

    /**
     * Name of directory to upload files.
     */
    private static final String NAME_UPLOAD_DIRECTORY = "upload";

    /**
     *
     */
    private static final String PATH_UPLOAD_DIRECTORY =
            "C:\\Users\\Kirill Tolkun\\IdeaProjects\\05_JavaST_2019\\"
                    + "task04XMLParser\\web\\"
                    + NAME_UPLOAD_DIRECTORY
                    + File.separator;
    /**
     * SAX parser.
     */
    private static final MedicinesSAXBuilder SAX_BUILDER
            = new MedicinesSAXBuilder();

    /**
     * StAX parser.
     */
    private static final MedicinesStAXBuilder STAX_BUILDER
            = new MedicinesStAXBuilder();

    /**
     * DOM parser.
     */
    private static final MedicinesDOMBuilder DOM_PARSER
            = new MedicinesDOMBuilder();

    /**
     * JAXB parser.
     */
    private static final MedicinesJAXBBuilder JAXB_PARSER
            = new MedicinesJAXBBuilder();

    /**
     * Handle get request.
     *
     * @param request  of page
     * @param response of page
     * @throws ServletException the servlet exception
     * @throws IOException      if jsp file isn't found
     */
    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response) {

        Map<String, String> cookies = new HashMap<>();
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
        }
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            switch (parameter) {
                case "parser_type":
                    try {
                        handleQueryParseType(request, cookies);
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                    break;
                case "language":
                    handleQueryLanguage(request, cookies);
                    break;
                case "update_table":
                    handleQueryClearTable(request, cookies);
                    break;
                default:
                    break;
            }
        }
        cookies.forEach((key, value) -> response.addCookie(
                new Cookie(key, value)
        ));
        request.setAttribute("language", cookies.get("language"));
        request.setAttribute("parser_type", cookies.get("parser_type"));
        HttpSession session = request.getSession();
        List<Medicine> medicines
                = (List<Medicine>) session.getAttribute("medicineObjects");
        request.setAttribute(
                "medicines",
                medicines
        );
        String language = null;
        if (cookies.get("language") != null) {
            language = cookies.get("language");
        } else {
            language = "en";
        }
        Locale currentLocale = new Locale(
                language
        );
        Config.set(request, Config.FMT_LOCALE, currentLocale);
        try {
            request
                    .getRequestDispatcher("/jsp/result.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Handle post request.
     *
     * @param request  of page
     * @param response of page
     * @throws ServletException the servlet exception
     * @throws IOException      if jsp file isn't found
     */
    @Override
    protected void doPost(final HttpServletRequest request,
                          final HttpServletResponse response) {
        Map<String, String> cookies = new HashMap<>();
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                cookies.put(cookie.getName(), cookie.getValue());
            }
        }
        Enumeration<String> parameters = request.getParameterNames();
        while (parameters.hasMoreElements()) {
            String parameter = parameters.nextElement();
            switch (parameter) {
                case "upload":
                    switch (request.getParameter(parameter)) {
                        case "table_files":
                            try {
                                handleQueryUploadTableFiles(
                                        request,
                                        cookies
                                );
                            } catch (Exception e) {
                                LOGGER.debug(e);
                            }
                            break;
                        default:
                            break;
                    }
                default:
                    break;
            }
        }

        cookies
                .forEach((key, value) -> response.addCookie(
                        new Cookie(key, value)
                ));
        request.setAttribute("language", cookies.get("language"));
        request.setAttribute("parser_type", cookies.get("parser_type"));
        HttpSession session = request
                .getSession();
        List<Medicine> medicines
                = (List<Medicine>) session.getAttribute("medicineObjects");
        request.setAttribute(
                "medicines",
                medicines
        );
        String language = null;
        if (cookies.get("language") != null) {
            language = cookies.get("language");
        } else {
            language = "en";
        }
        Locale currentLocale = new Locale(
                language
        );
        Config.set(request, Config.FMT_LOCALE, currentLocale);
        try {
            request
                    .getRequestDispatcher("/jsp/result.jsp")
                    .forward(request, response);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Handle {@code language} query.
     *
     * @param request of page
     * @param cookies of page
     */
    private void handleQueryLanguage(final HttpServletRequest request,
                                     final Map<String, String> cookies
    ) {
        String requestLanguage = request.getParameter("language");
        if (requestLanguage == null) {
            cookies.putIfAbsent("language", "en");
        } else {
            cookies.put("language", requestLanguage);
        }
    }

    /**
     * Handle {@code parse_type} query.
     *
     * @param request of page
     * @param cookies of page
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws ParserConfigurationException if sax builder can not be created
     * @throws XMLStreamException           used to report well-formedness
     *                                      errors as well as unexpected
     *                                      processing conditions.
     * @throws JAXBException                if an error is encountered while
     *                                      creating the JAXBSource or if either
     *                                      of the parameters are null.
     */
    private void handleQueryParseType(
            final HttpServletRequest request,
            final Map<String, String> cookies
    ) throws ParserConfigurationException, XMLStreamException, SAXException,
            JAXBException, IOException {
        String requestParserType = request.getParameter("parser_type");
        if (requestParserType == null) {
            cookies.putIfAbsent("parser_type", "sax");
        } else {
            cookies.put("parser_type", requestParserType);
        }
        List<Medicine> medicines = parseMedicines(
                cookies.get("tableXmlFileName"),
                cookies.get("tableXsdFileName"),
                cookies.get("parser_type")
        );
        request.getSession().setAttribute("medicineObjects", medicines);
    }

    /**
     * Handle {@code upload} query.
     *
     * @param request of page
     * @param cookies of page
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws ParserConfigurationException if sax builder can not be created
     * @throws XMLStreamException           used to report well-formedness
     *                                      errors as well as unexpected
     *                                      processing conditions.
     * @throws JAXBException                if an error is encountered while
     *                                      creating the JAXBSource or if either
     *                                      of the parameters are null.
     * @throws ServletException             the servlet exception
     */
    private void handleQueryUploadTableFiles(
            final HttpServletRequest request,
            final Map<String, String> cookies
    ) throws IOException, ServletException, ParserConfigurationException,
            JAXBException, SAXException, XMLStreamException {
        String uploadPath = getServletContext().getRealPath("")
                + File.separator + NAME_UPLOAD_DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }
        List<Part> parts = request.getParts()
                .stream()
                .filter(part -> XML_FILE_PATTERN
                        .matcher(part.getSubmittedFileName())
                        .matches()
                        || XSD_FILE_PATTERN
                        .matcher(part.getSubmittedFileName())
                        .matches()
                )
                .collect(Collectors.toList());
        if (parts.size() != 2) {
            return;
        }
        File oldTableXmlFile
                = new File(
                PATH_UPLOAD_DIRECTORY
                        + cookies.get("tableXmlFileName")
        );
        oldTableXmlFile.delete();
        File oldTableXsdFile
                = new File(
                PATH_UPLOAD_DIRECTORY
                        + cookies.get("tableXsdFileName")
        );
        oldTableXsdFile.delete();
        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName().equals("")) {
                return;
            }
            String fileName = request.getSession().getId()
                    + part.getSubmittedFileName();
            if (part.getName().equals("xmlTableFile")) {
                cookies.putIfAbsent("tableXmlFileName", fileName);
                cookies.put("tableXmlFileName", fileName);
            } else {
                cookies.putIfAbsent("tableXsdFileName", fileName);
                cookies.put("tableXsdFileName", fileName);
            }
            String filePath = uploadPath + File.separator + fileName;
            part.write(filePath);
        }
        handleQueryParseType(request, cookies);
    }

    /**
     * Clear table on the page.
     *
     * @param request of page
     * @param cookies of page
     */
    private void handleQueryClearTable(
            final HttpServletRequest request,
            final Map<String, String> cookies
    ) {
        request.getSession().setAttribute("medicineObjects", null);
    }

    /**
     * Parse xml depending on type of parser.
     *
     * @param xmlFileName the name of xml file
     * @param xsdFileName the name of xsd file
     * @param parserType  the type of parser
     * @return list of medicines
     * @throws SAXException                 if xml file doesn't correspond to
     *                                      xsd scheme
     * @throws IOException                  if {@code xmlFilePath} of
     *                                      {@code xsdFilePath} is invalid
     * @throws ParserConfigurationException if sax builder can not be created
     * @throws XMLStreamException           used to report well-formedness
     *                                      errors as well as unexpected
     *                                      processing conditions.
     * @throws JAXBException                if an error is encountered while
     *                                      creating the JAXBSource or if either
     *                                      of the parameters are null.
     */
    private List<Medicine> parseMedicines(final String xmlFileName,
                                          final String xsdFileName,
                                          final String parserType)
            throws ParserConfigurationException, SAXException, IOException,
            XMLStreamException, JAXBException {
        List<Medicine> medicines = new ArrayList<>();
        if (xmlFileName != null && xsdFileName != null) {
            switch (parserType) {
                case "sax":
                    SAX_BUILDER.buildMedicines(
                            PATH_UPLOAD_DIRECTORY + xmlFileName,
                            PATH_UPLOAD_DIRECTORY + xsdFileName
                    );
                    medicines = SAX_BUILDER.getMedicines().getMedicine();
                    break;
                case "stax":
                    STAX_BUILDER.buildMedicines(
                            PATH_UPLOAD_DIRECTORY + xmlFileName,
                            PATH_UPLOAD_DIRECTORY + xsdFileName
                    );
                    medicines = STAX_BUILDER.getMedicines().getMedicine();
                    break;
                case "dom":
                    DOM_PARSER.buildMedicines(
                            PATH_UPLOAD_DIRECTORY + xmlFileName,
                            PATH_UPLOAD_DIRECTORY + xsdFileName
                    );
                    medicines = DOM_PARSER.getMedicines().getMedicine();
                    break;
                case "jaxb":
                    JAXB_PARSER.buildMedicines(
                            PATH_UPLOAD_DIRECTORY + xmlFileName,
                            PATH_UPLOAD_DIRECTORY + xsdFileName
                    );
                    medicines = JAXB_PARSER.getMedicines().getMedicine();
                    break;
                default:
                    break;
            }
            LOGGER.debug("Parsed by {}.", parserType);
        }
        return medicines;
    }
}
