module Proyecto_Final {
	requires transitive javafx.controls;
	requires javafx.fxml;
	requires transitive javafx.graphics;
	requires javafx.base;
	requires transitive java.sql;
	requires jakarta.persistence;
	requires org.hibernate.orm.core;
	requires java.desktop;
	requires javafx.swing;
	requires bcrypt;
	requires jakarta.mail;
	requires java.naming;
	requires javafx.media;
	
	opens org.proyecto.vo to org.hibernate.orm.core;
	
	exports org.proyecto;
	exports org.proyecto.controler;
	exports org.proyecto.ui;
	exports org.proyecto.ui.paneles;
	exports org.proyecto.vo;
	exports org.proyecto.dao.impl;
}