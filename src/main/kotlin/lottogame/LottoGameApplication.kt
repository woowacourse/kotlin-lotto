package lottogame

import lottogame.controller.LottoGameController
import lottogame.model.generator.DefaultAutoLottieGenerator
import lottogame.model.generator.DefaultLottoMachine
import lottogame.model.generator.DefaultManualLottieGenerator
import lottogame.view.ConsoleLottoGameInputView
import lottogame.view.ConsoleLottoGameOutputView

fun main() {
    Thread.setDefaultUncaughtExceptionHandler { _, exception ->
        exception.printStackTrace()
    }
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        DefaultLottoMachine(DefaultAutoLottieGenerator(), DefaultManualLottieGenerator()),
    ).startLottoGame()
}
