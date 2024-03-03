package lotto

import lotto.controller.Controller

fun main() {
    val controller = Controller()
    controller.purchaseLottos()
    controller.enterWinningNumber()
    controller.checkResult()
}
