package com.github.rkbalgi.grpccs

import javafx.application.Application
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.layout.HBox
import javafx.stage.Stage
import kotlin.concurrent.thread

class GrpccsApplication : Application() {
    override fun start(p0: Stage) {

        val root = HBox()
        root.children.add(Label("Hello JavaFX"))

        val startServerBtn = Button("Start Server")
        startServerBtn.onMouseClicked = EventHandler {
            println("x = ${it.x} y = ${it.y}")
            thread {
                startServer()
            }
        }
        root.children.add(startServerBtn)

        p0.scene = Scene(root, 400.0, 400.0)
        p0.onCloseRequest = EventHandler {
            stop()
            server?.shutdownNow()
        }
        p0.show()
    }

/*    companion object {
        fun main(args: Array<String>) {
            launch(*args)
        }
    }*/

}