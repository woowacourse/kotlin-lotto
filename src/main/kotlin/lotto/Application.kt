package lotto

import lotto.controller.World

fun main() {
    runCatching {
        val world = World()
        world.processLotto()
    }.onFailure {
        println("[ERROR]: " + it.message)
    }
}
