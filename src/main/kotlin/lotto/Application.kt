package lotto

import lotto.controller.WorldController

fun main() {
    runCatching {
        val world = WorldController()
        world.processLotto()
    }.onFailure {
        println("[ERROR]: " + it.message)
        println(it.stackTraceToString())
    }
}
