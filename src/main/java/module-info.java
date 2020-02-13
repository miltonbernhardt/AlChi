open module App {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.persistence;
	requires org.hibernate.orm.core;
	requires org.hibernate.commons.annotations;
	requires java.logging;
	requires java.sql;
	requires java.desktop;

    exports app;
}