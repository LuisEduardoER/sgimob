<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SGIMOB - Sistema de Gerenciamento Imobiliario</title>
</head>
<body>
<f:view>
	<h:form>
		<rich:toolBar>
			<rich:dropDownMenu value="Administração">
				<rich:menuItem value="Inquilino" action="cadastrarInquilinoAdmin" />
				<rich:menuItem value="Proprietario"
					action="cadastrarProprietarioAdmin" />
				<rich:menuItem value="Imovel" action="cadastrarImovelAdmin" />
				<rich:menuItem value="Tipo de Imóvel"
					action="cadastrarTipoImovelAdmin" />
			</rich:dropDownMenu>
		</rich:toolBar>
		<rich:panel styleClass="panelPrincipal">
			<center><rich:panel
				style="width:300px; margin-top:50px;margin-bottom:50px;">
				<h3>Cadastro de Proprietário</h3>
				<rich:messages />
				<h:panelGrid columns="2">
					<h:outputText value="Nome" />
					<h:inputText value="#{proprietarioBean.proprietario.nome}" />
					<h:outputText value="Data de Nascimento" />
					<rich:calendar
						value="#{proprietarioBean.proprietario.data_nascimento}"
						locale="pt_BR" />
					<h:outputText value="Endereço" />
					<h:inputText value="#{proprietarioBean.proprietario.endereco}" />
					<h:outputText value="Telefone" />
					<h:inputText value="#{proprietarioBean.proprietario.telefone}" />
				</h:panelGrid>
				<h:commandButton value="Cadastrar"
					action="#{proprietarioBean.incluir}" />
			</rich:panel></center>
		</rich:panel>
	</h:form>
</f:view>
</body>
</html>