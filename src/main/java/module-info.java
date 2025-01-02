module bb.j2ray.v2rayjui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.yaml.snakeyaml;
    requires com.alibaba.fastjson2;
    requires org.apache.commons.codec;
    requires java.net.http;
    requires commons.httpclient;

    opens bb.j2ray.v2rayjui to javafx.fxml;
    exports bb.j2ray.v2rayjui;
}