<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://myfaces.apache.org/tomahawk" prefix="t"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Accueil" markupType="html"
		sidebarPosition="left" sidebarWidth="250">

		<f:facet name="header">
			<%@ include file="layout/header.jspf"%>
		</f:facet>

		<f:facet name="sidebar">
			<%@ include file="layout/sidebar.jspf"%>
		</f:facet>

		<f:facet name="footer">
			<%@ include file="layout/footer.jspf"%>
		</f:facet>



		<h1 style="color: rgb(128, 175, 220); text-align: center;">
		Globus University</h1>

		<br />

		<h3 style="color: black; text-align: center;">Application de
		gestion de planning TIMETABLEASY</h3>

		<br />
		<br />
		<h:form style="width: 220px ; margin: auto;">
		<h:graphicImage url="images/globusuniversityBIG.png"></h:graphicImage>
		
		</h:form>



	</rich:page>



</f:view>