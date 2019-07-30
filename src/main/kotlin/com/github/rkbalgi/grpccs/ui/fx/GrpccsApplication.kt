package com.github.rkbalgi.grpccs.ui.fx

import com.github.rkbalgi.grpccs.server
import com.github.rkbalgi.grpccs.startServer
import javafx.application.Application
import javafx.event.EventHandler
import javafx.geometry.HPos
import javafx.geometry.Pos
import javafx.geometry.VPos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.input.KeyCombination
import javafx.scene.layout.FlowPane
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.Region
import javafx.stage.Stage
import kotlin.concurrent.thread

class GrpccsApplication : Application() {
    override fun start(p0: Stage) {

        val root = GridPane()
        root.style = "-fx-background-color: yellow"
        //root.background=Background(BackgroundFill(Color.web("#776654")))

        root.setPrefSize(800.0, 600.0)
        root.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE)

        root.isGridLinesVisible = true


        //root.children.add(Label("Hello JavaFX"))

        val startServerBtn = Button("Start Server")
        startServerBtn.onMouseClicked = EventHandler {
            println("x = ${it.x} y = ${it.y}")
            thread {
                startServer()
            }
        }

        val treePane = FlowPane()

        val redisPane = buildRedisPane()


        GridPane.setConstraints(treePane, 1, 1, 10, 10)
        //GridPane.setConstraints(startServerBtn, 5, 10, 5, 5)
        GridPane.setConstraints(redisPane, 12, 1, 5, 10, HPos.CENTER, VPos.CENTER)

        root.children.addAll(treePane, redisPane, startServerBtn)
        //root.minHeight = 400.0
        //root.minWidth = 400.0


        p0.scene = Scene(root, 800.0, 600.0)
        val menubar = MenuBar()
        val fileMenu = Menu("File")
        fileMenu.items.add(MenuItem("Quit").apply {
            accelerator = KeyCombination.valueOf("Alt+Q")
            setOnAction { System.exit(0) }
        })
        menubar.menus.add(fileMenu)
        root.children.addAll(menubar)

        p0.title = "GRPCCS v1.0"

        p0.onCloseRequest = EventHandler {
            stop()
            server?.shutdownNow()
        }
        p0.show()
    }


}


fun buildRedisPane(): Pane {
    val redisPane = GridPane()

    //redisPane.isGridLinesVisible = true
    redisPane.alignment = Pos.CENTER

    val redisTextArea = TextArea()
    redisTextArea.prefRowCount = 20
    redisTextArea.prefColumnCount = 10
    val fetchBtb = Button("Fetch")
    fetchBtb.onMouseClicked = EventHandler {
        redisTextArea.appendText("Hello! \n")
    }

    val scrollPane = ScrollPane()
    scrollPane.content = redisTextArea

    GridPane.setConstraints(redisTextArea, 0, 0, 10, 10)
    GridPane.setConstraints(fetchBtb, 2, 11, 10, 2, HPos.CENTER, VPos.CENTER)


    redisPane.children.addAll(redisTextArea, fetchBtb)

    return redisPane

}


