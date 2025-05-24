module AplicacionExtraTFG {
	exports org.aplicacion;
	exports org.aplicacion.dao.impl;
	exports org.aplicacion.util;
	exports org.aplicacion.dao;
	exports org.aplicacion.vo;
	exports org.aplicacion.controller;
	exports org.aplicacion.ui;
	exports org.aplicacion.ui.paneles;

	requires transitive jakarta.persistence;
	requires transitive javafx.graphics;
	requires transitive javafx.controls;
	requires org.hibernate.orm.core;
	requires java.sql;
	requires javafx.swing;
	requires bcrypt;
	
	opens org.aplicacion.vo to org.hibernate.orm.core;
}