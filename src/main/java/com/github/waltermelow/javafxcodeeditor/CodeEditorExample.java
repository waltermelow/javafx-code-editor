package com.github.waltermelow.javafxcodeeditor;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * An example application which demonstrates use of a
 * CodeMirror based JavaScript CodeEditor wrapped in
 * a JavaFX WebView.
 */
public class CodeEditorExample extends Application {
    // some sample code to be edited.
    static final private String editingCode =
            "import javafx.application.Application;\n" +
                    "import javafx.scene.Scene;\n" +
                    "import javafx.scene.web.WebView;\n" +
                    "import javafx.stage.Stage;\n" +
                    "\n" +
                    "/** Sample code editing application wrapping an editor in a WebView. */\n" +
                    "public class CodeEditorExample extends Application {\n" +
                    "  public static void main(String[] args) { launch(args); }\n" +
                    "  @Override public void start(Stage stage) throws Exception {\n" +
                    "    WebView webView = new WebView();\n" +
                    "    webView.getEngine().load(\"http://codemirror.net/mode/groovy/index.html\");\n" +
                    "    final Scene scene = new Scene(webView);\n" +
                    "    webView.prefWidthProperty().bind(scene.widthProperty());\n" +
                    "    webView.prefHeightProperty().bind(scene.heightProperty());\n" +
                    "    stage.setScene(scene);\n" +
                    "    stage.show();\n" +
                    "  }\n" +
                    "}";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // create the editing controls.
        Label title = new Label("Editing: CodeEditor.java");
        title.setStyle("-fx-font-size: 20;");
        final Label labeledCode = new Label(editingCode);
        final CodeEditor editor = new CodeEditor(editingCode);
        final Button revertEdits = new Button("Revert edits");
        revertEdits.setOnAction(actionEvent -> editor.revertEdits());
        final Button copyCode = new Button("Take a snapshot from the editor and set a revert point");
        copyCode.setOnAction(actionEvent -> {
            labeledCode.setText(editor.getCodeAndSnapshot());
            System.out.println(editor.getCodeAndSnapshot());
        });

        // layout the scene.
        final VBox layout = new VBox(10,
                title,
                editor,
                new HBox(10, copyCode, revertEdits),
                labeledCode
        );
        layout.setStyle("-fx-background-color: cornsilk; -fx-padding: 10;");

        // display the scene.
        final Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
}