<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
	<rich:page theme="simple" pageTitle="Error Authentification"
		markupType="html" sidebarPosition="left" sidebarWidth="250">

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
			<h:outputText value="Side Bar" />
			<!-- side panel content -->

		</f:facet>

		<f:facet name="footer">
			<h:outputText
				value="TIMETABLEASY Application - à usage interne © Copyright Globus University 2010 All Right reserved, Reproduction in whole or in part is prohibited without the written consent of Globus University "></h:outputText>
		</f:facet>

		<rich:layout>

			<rich:layoutPanel position="top">

				<!--top-->

			</rich:layoutPanel>

			<rich:layoutPanel position="left">

				<!--left-->

			</rich:layoutPanel>

			<rich:layoutPanel position="center">
				<br />
				<br />
				<br />
				<!--center-->
                 Failed Authentification

        </rich:layoutPanel>

			<rich:layoutPanel position="right">

				<!--right-->

			</rich:layoutPanel>

			<rich:layoutPanel position="bottom">

				<!--bottom-->

			</rich:layoutPanel>

		</rich:layout>



	</rich:page>



</f:view>