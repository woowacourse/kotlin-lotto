package lotto

import lotto.controller.Controller
import lotto.domain.RandomLotteryNumberGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val controller = Controller(
        RandomLotteryNumberGenerator(), InputView(), OutputView()
    )
    controller.run()
}
