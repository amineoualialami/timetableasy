<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Modele" markupType="html"
		sidebarPosition="left" sidebarWidth="250">

		<f:facet name="header">

			<rich:layout>
				<rich:layoutPanel position="top">
				</rich:layoutPanel>
				<rich:layoutPanel position="left">
					<h:graphicImage url="images/globusuniversity.png"></h:graphicImage>
				</rich:layoutPanel>
				<rich:layoutPanel position="center">
				</rich:layoutPanel>
				<rich:layoutPanel position="right">
					<br />
					<br />
					<h:graphicImage url="images/timetableasy.png"></h:graphicImage>
				</rich:layoutPanel>
				<rich:layoutPanel position="bottom">
				</rich:layoutPanel>
			</rich:layout>
		</f:facet>

		<f:facet name="sidebar">
			<rich:panelMenu mode="ajax">
				<rich:panelMenuGroup label="Administration" action="#{bean.action}"
					expanded="true" disabled="#{user.bool2}">
					<rich:panelMenuItem label="Utilisateurs" reRender="test"
						action="#{user.listUtilisateurs}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>

					<rich:panelMenuItem label="Cursus" reRender="test"
						action="#{cursus.listCursus}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>
					<rich:panelMenuItem label="Campus" reRender="test"
						action="#{campus.listCampus}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>
				</rich:panelMenuGroup>
				<rich:panelMenuGroup label="Test" action="#{bean.action}"
					expanded="true" disabled="#{user.bool}">
					<rich:panelMenuItem label="test 1" reRender="test"
						action="#{espace.listEspaces}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>
					<rich:panelMenuItem label="test 2" reRender="test"
						action="#{espace.listEspaces}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>
				</rich:panelMenuGroup>
				<rich:panelMenuGroup label="Gestion du Cursus"
					action="#{bean.action}" expanded="true" disabled="#{user.bool3}">
					<rich:panelMenuItem label="Cours" reRender="test"
						action="#{cour.listCours}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>
					<rich:panelMenuItem label="Periode d'etude" reRender="test"
						action="#{periodeDetude.listPeriodeDetude}">
						<f:param value="test value" name="test" />
					</rich:panelMenuItem>
				</rich:panelMenuGroup>
			</rich:panelMenu>
		</f:facet>

		<f:facet name="footer">
			<h:outputText
				value="TIMETABLEASY Application - à usage interne © Copyright Globus University 2010 All Right reserved, Reproduction in whole or in part is prohibited without the written consent of Globus University "></h:outputText>
		</f:facet>




	</rich:page>



</f:view>