package lotto

import lotto.controller.LottoController
import lotto.model.LottoNumbersGenerator
import lotto.view.InputView
import lotto.view.OutputView

fun main() {
    val lottoController = LottoController(InputView(), OutputView(), LottoNumbersGenerator())
    lottoController.run()
}
