<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt" prefix="fmt" %>
<fmt:setBundle basename="xmlparser"/>
<fmt:message key="table.medicine.name" var="tableMedicineName"/>
<fmt:message key="table.medicine.group" var="tableMedicineGroup"/>
<fmt:message key="table.medicine.analogs" var="tableMedicineAnalogs"/>
<fmt:message key="table.medicine.versions" var="tableMedicineVersions"/>
<fmt:message key="table.medicine.versions.company"
             var="tableMedicineVersionsCompany"/>
<fmt:message key="table.medicine.versions.company.name"
             var="tableMedicineVersionsCompanyName"/>
<fmt:message key="table.medicine.versions.company.package"
             var="tableMedicineVersionsCompanyPackage"/>
<fmt:message key="table.medicine.versions.company.package.type"
             var="tableMedicineVersionsCompanyPackageType"/>
<fmt:message key="table.medicine.versions.company.package.quantity"
             var="tableMedicineVersionsCompanyPackageQuantity"/>
<fmt:message key="table.medicine.versions.company.package.price"
             var="tableMedicineVersionsCompanyPackagePrice"/>
<fmt:message key="table.medicine.versions.company.dosage"
             var="tableMedicineVersionsCompanyDosage"/>
<fmt:message key="table.medicine.versions.company.dosage.size"
             var="tableMedicineVersionsCompanyDosageSize"/>
<fmt:message key="table.medicine.versions.company.dosage.period"
             var="tableMedicineVersionsCompanyDosagePeriod"/>
<fmt:message key="table.medicine.versions.company.certificate"
             var="tableMedicineVersionsCompanyCertificate"/>
<fmt:message key="table.medicine.versions.company.certificate.number"
             var="tableMedicineVersionsCompanyCertificateNumber"/>
<fmt:message key="table.medicine.versions.company.certificate.date_issue"
             var="tableMedicineVersionsCompanyCertificateDateIssue"/>
<fmt:message key="table.medicine.versions.company.certificate.date_expiration"
             var="tableMedicineVersionsCompanyCertificateDateExpiration"/>
<fmt:message key="table.medicine.versions.company.certificate.reg_organization"
             var="tableMedicineVersionsCompanyCertificateRegOrganization"/>
<!DOCTYPE html>
<html lang="${ language }">
<head>
    <title>Result Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="favicon.ico">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<header>
    <script>
        window.history.pushState({}, document.title, window.location.pathname);

    </script>
    <form name="upload" action="?upload=table_files" method="post"
          enctype="multipart/form-data" class="form-upload-table">
        <input id="xmlTableFile" name="xmlTableFile" class="hide-inputfile"
               type="file" accept=".xml">
        <label for="xmlTableFile"><img
                src="img/icon/tableXmlFileIcon.png"></label>
        <input id="xsdTableFile" name="xsdTableFile" class="hide-inputfile"
               type="file" accept=".xsd">
        <label for="xsdTableFile"><img
                src="img/icon/tableXsdFileIcon.png"></label>
        <button><img src="img/icon/uploadButtonIcon.png"></button>
    </form>
    <form name="clear" method="GET" class="form-clear-table">

        <button name="update_table" value="clear"><img
                src="img/icon/tableClearIcon.png"></button>
    </form>
    <div class="select-group">
        <form name="locale">
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en'  ? 'selected' : ''}>
                    English
                </option>
                <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian
                </option>
                <option value="be" ${language == 'be' ? 'selected' : ''}>
                    Belorussian
                </option>
                <option value="zh" ${language == 'zh' ? 'selected' : ''}>Chinese
                </option>
            </select>
        </form>
        <form>
            <select id="parser_type" name="parser_type" onchange="submit()">
                <option value="sax" ${parser_type == 'sax' ? 'selected' : ''}>
                    SAX
                    parser
                </option>
                <option value="stax" ${parser_type == 'stax' ? 'selected' : ''}>
                    StAX
                    parser
                </option>
                <option value="dom" ${parser_type == 'dom' ? 'selected' : ''}>
                    DOM
                    Parser
                </option>
                <option value="jaxb" ${parser_type == 'jaxb' ? 'selected' : ''}>
                    JAXB
                    Parser
                </option>
            </select>
        </form>
    </div>
</header>
<main>
    <table class="rwd-table">
        <thead>
        <tr>
            <th rowspan="3">${ tableMedicineName }</th>
            <th rowspan="3">${ tableMedicineGroup }</th>
            <th rowspan="3">${ tableMedicineAnalogs }</th>
            <th rowspan="3">${ tableMedicineVersions }</th>
            <th colspan="10">${ tableMedicineVersionsCompany }</th>
        </tr>
        <tr>
            <th rowspan="2">${ tableMedicineVersionsCompanyName }</th>
            <th colspan="3">${ tableMedicineVersionsCompanyPackage }</th>
            <th colspan="2">${ tableMedicineVersionsCompanyDosage }</th>
            <th colspan="4">${ tableMedicineVersionsCompanyCertificate }</th>
        </tr>
        <tr>
            <th>${ tableMedicineVersionsCompanyPackageType }</th>
            <th>${ tableMedicineVersionsCompanyPackageQuantity }</th>
            <th>${ tableMedicineVersionsCompanyPackagePrice }</th>
            <th>${ tableMedicineVersionsCompanyDosageSize }</th>
            <th>${ tableMedicineVersionsCompanyDosagePeriod }</th>
            <th>${ tableMedicineVersionsCompanyCertificateNumber }</th>
            <th>${ tableMedicineVersionsCompanyCertificateDateIssue }</th>
            <th>${ tableMedicineVersionsCompanyCertificateDateExpiration }</th>
            <th>${ tableMedicineVersionsCompanyCertificateRegOrganization }</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="medicine" items="${medicines}" varStatus="status">
            <tr>
                <td data-label="${ tableMedicineName }"
                    rowspan="<c:out value="${ medicine.versions.getCompanyNumber() }"/>">
                    <c:out value="${ medicine.name }"/>
                </td>
                <td data-label="${ tableMedicineGroup }"
                    rowspan="<c:out value="${ medicine.versions.getCompanyNumber() }"/>">
                    <c:out value="${ medicine.group }"/>
                </td>
                <td data-label="${ tableMedicineAnalogs }"
                    rowspan="<c:out value="${ medicine.versions.getCompanyNumber() }"/>"
                    class="analogs">
                    <c:choose>
                        <c:when test="${medicine.analogs.size() != 0}">
                            <c:out value="${ medicine.analogs }"/>
                        </c:when>
                        <c:otherwise>
                            &mdash;
                        </c:otherwise>
                    </c:choose>
                </td>
                <c:forEach var="version" items="${medicine.versions.version}"
                           varStatus="status" begin="0" end="0">
                    <td data-label="${ tableMedicineVersions }"
                        rowspan="<c:out value="${ version.companyOrCompanyCertificate.size() }"/>">
                        <c:out value="${ version.type }"/>
                    </td>
                    <c:forEach var="company"
                               items="${ version.companyOrCompanyCertificate }"
                               varStatus="status" begin="0" end="0">
                        <td data-label="${ tableMedicineVersionsCompanyName }">
                            <c:out
                                    value="${ company.name }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyPackageType }">
                            <c:out
                                    value="${ company.getPackage().type }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyPackageQuantity }">
                            <c:out
                                    value="${ company.getPackage().quantity }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyPackagePrice }">
                            <c:out
                                    value="${ company.getPackage().price.value } ${ company.getPackage().price.currency }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyDosageSize }">
                            <c:out
                                    value="${ company.dosage.size }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyDosagePeriod }">
                            <c:out
                                    value="${ company.dosage.period.value } ${ company.dosage.period.type }"/></td>
                        <c:choose>
                            <c:when test="${ company.getClass() eq 'class by.tolkun.xmlparser.entity.medicine.CertificateCompany' }">
                                <td data-label="${ tableMedicineVersionsCompanyCertificateNumber} ">
                                    <c:out
                                            value="${ company.certificate.number }"/></td>
                                <td data-label="${ tableMedicineVersionsCompanyCertificateDateIssue }">
                                    <c:out
                                            value="${ company.certificate.dateIssue }"/></td>
                                <td data-label="${ tableMedicineVersionsCompanyCertificateDateExpiration }">
                                    <c:out value="${ company.certificate.dateExpiration }"/></td>
                                <td data-label="${ tableMedicineVersionsCompanyCertificateRegOrganization }">
                                    <c:out value="${ company.certificate.regOrganization }"/></td>
                            </c:when>
                            <c:otherwise>
                                <td data-label="${ tableMedicineVersionsCompanyCertificate }"
                                    colspan="4" class="no-certificate">&mdash;
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:forEach>
            </tr>
            <c:forEach var="version" items="${medicine.versions.version}"
                       varStatus="versionStatus" begin="0"
                       end="${medicine.versions.version.size()}">
                <c:if test="${ versionStatus.count >= 2 }">
                    <tr>
                        <td data-label="${ tableMedicineVersions }"
                            rowspan="<c:out value="${ version.companyOrCompanyCertificate.size() }"/>">
                            <c:out value="${ version.type }"/>
                        </td>
                        <c:forEach var="company"
                                   items="${ version.companyOrCompanyCertificate }"
                                   varStatus="status" begin="0" end="0">
                            <td data-label="${ tableMedicineVersionsCompanyName }">
                                <c:out
                                        value="${ company.name }"/></td>
                            <td data-label="${ tableMedicineVersionsCompanyPackageType }">
                                <c:out
                                        value="${ company.getPackage().type }"/></td>
                            <td data-label="${ tableMedicineVersionsCompanyPackageQuantity }">
                                <c:out
                                        value="${ company.getPackage().quantity }"/></td>
                            <td data-label="${ tableMedicineVersionsCompanyPackagePrice }">
                                <c:out
                                        value="${ company.getPackage().price.value } ${ company.getPackage().price.currency }"/></td>
                            <td data-label="${ tableMedicineVersionsCompanyDosageSize }">
                                <c:out
                                        value="${ company.dosage.size }"/></td>
                            <td data-label="${ tableMedicineVersionsCompanyDosagePeriod }">
                                <c:out
                                        value="${ company.dosage.period.value } ${ company.dosage.period.type }"/></td>
                            <c:choose>
                                <c:when test="${ company.getClass() eq 'class by.tolkun.xmlparser.entity.medicine.CertificateCompany' }">
                                    <td data-label="${ tableMedicineVersionsCompanyCertificateNumber}">
                                        <c:out
                                                value="${ company.certificate.number }"/></td>
                                    <td data-label="${ tableMedicineVersionsCompanyCertificateDateIssue }">
                                        <c:out value="${ company.certificate.dateIssue }"/></td>
                                    <td data-label="${ tableMedicineVersionsCompanyCertificateDateExpiration }">
                                        <c:out value="${ company.certificate.dateExpiration }"/></td>
                                    <td data-label="${ tableMedicineVersionsCompanyCertificateRegOrganization }">
                                        <c:out value="${ company.certificate.regOrganization }"/></td>
                                </c:when>
                                <c:otherwise>
                                    <td data-label="${ tableMedicineVersionsCompanyCertificate }"
                                        colspan="4" class="no-certificate">
                                        &mdash;
                                    </td>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </tr>
                </c:if>
                <c:forEach var="company"
                           items="${ version.companyOrCompanyCertificate }"
                           varStatus="status" begin="1">
                    <tr>
                        <td data-label="${ tableMedicineVersionsCompanyName }">
                            <c:out
                                    value="${ company.name }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyPackageType }">
                            <c:out
                                    value="${ company.getPackage().type }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyPackageQuantity }">
                            <c:out
                                    value="${ company.getPackage().quantity }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyPackagePrice }">
                            <c:out
                                    value="${ company.getPackage().price.value } ${ company.getPackage().price.currency }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyDosageSize }">
                            <c:out
                                    value="${ company.dosage.size }"/></td>
                        <td data-label="${ tableMedicineVersionsCompanyDosagePeriod }">
                            <c:out
                                    value="${ company.dosage.period.value } ${ company.dosage.period.type }"/></td>
                        <c:choose>
                            <c:when test="${ company.getClass() eq 'class by.tolkun.xmlparser.entity.medicine.CertificateCompany' }">
                                <td data-label="${ tableMedicineVersionsCompanyCertificateNumber}">
                                    <c:out
                                            value="${ company.certificate.number }"/></td>
                                <td data-label="${ tableMedicineVersionsCompanyCertificateDateIssue }">
                                    <c:out
                                            value="${ company.certificate.dateIssue }"/></td>
                                <td data-label="${ tableMedicineVersionsCompanyCertificateDateExpiration }">
                                    <c:out
                                            value="${ company.certificate.dateExpiration }"/></td>
                                <td data-label="${ tableMedicineVersionsCompanyCertificateRegOrganization }">
                                    <c:out
                                            value="${ company.certificate.regOrganization }"/></td>
                            </c:when>
                            <c:otherwise>
                                <td data-label="${ tableMedicineVersionsCompanyCertificate }"
                                    colspan="4" class="no-certificate">&mdash;
                                </td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </c:forEach>
        </c:forEach>
        </tbody>
    </table>
</main>
</body>
</html>