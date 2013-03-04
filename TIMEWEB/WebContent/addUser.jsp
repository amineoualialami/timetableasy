<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="f"  uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h"  uri="http://java.sun.com/jsf/html"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
<rich:page theme="simple" pageTitle="Ajouter Utilisateur" markupType="html" sidebarPosition="left" sidebarWidth="250" >

    <f:facet name="header" >

        <!-- header  content -->
        <h:outputText value="Header" />
    </f:facet>

    <f:facet name="sidebar">
       <rich:panelMenu mode="ajax"  >
            <rich:panelMenuGroup label="Administration" action="#{bean.action}" expanded="true" disabled="#{user.bool2}"> 
                <rich:panelMenuItem label="Utilisateurs" reRender="test" action="#{user.listUtilisateurs}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
               
                <rich:panelMenuItem label="Cursus" reRender="test" action="#{cursus.listCursus}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
                <rich:panelMenuItem label="Campus" reRender="test" action="#{campus.listCampus}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
             </rich:panelMenuGroup>
             <rich:panelMenuGroup label="Gestion du Campus" action="#{bean.action}" expanded="true" disabled="#{user.bool}"> 
                <rich:panelMenuItem label="Espaces" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem> 
                <rich:panelMenuItem label="Classes" reRender="test" action="#{espace.listEspaces}"> 
                        <f:param value="test value" name="test"/> 
                </rich:panelMenuItem>
             </rich:panelMenuGroup>
         </rich:panelMenu>

    </f:facet>

    <f:facet name="footer">
         <h:outputText value="Footer" />
        <!-- footer  content -->

    </f:facet>
    <a4j:form>
                        <rich:panel header="Ajouter un nouveau Utilisateur"  >
                               <h:panelGrid  columns="2">
                               
                                      <h:outputText value="Nom :" />
                                      <h:inputText value="#{user.nom}" id="nom" />
                                      <h:outputText value="Prenom :" />
                                      <h:inputText value="#{user.prenom}" id="prenom" />
                                      
                                      <h:outputText value="Login :" />
                                      <h:inputText value="#{user.login}" id="login" />
                                      <h:outputText value="Password :" />
                                      <h:inputText value="#{user.password}" id="password" />
                                      
                                       <h:outputText value="Roles :" />
                                      <rich:pickList value="#{user.newRolesSL}">
                                        <f:selectItems value="#{user.allrolesSL}" />
                                     </rich:pickList>
                                      
                                      
                                      <f:facet name="footer">
                                         <a4j:commandButton value="OK" action="#{user.persistUser}"/>
                                      </f:facet>
                               </h:panelGrid>
                        </rich:panel>
     </a4j:form>
  
     
                  

</rich:page>


    
</f:view>