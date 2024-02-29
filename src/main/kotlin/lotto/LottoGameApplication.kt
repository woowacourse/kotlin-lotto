package lotto

import lotto.controller.LottoGameController
import lotto.model.RandomNumbersGenerator
import lotto.util.ExceptionHandler
import lotto.view.ConsoleLottoGameInputView
import lotto.view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        ExceptionHandler(),
        RandomNumbersGenerator(),
    ).startLottoGame()
}
