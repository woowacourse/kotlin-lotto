package lotto

import lotto.controller.LottoController
import lotto.model.lottomachine.AutomaticLottoMachine
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val lottoController = LottoController(InputView(), OutputView())
    lottoController.run()
}
