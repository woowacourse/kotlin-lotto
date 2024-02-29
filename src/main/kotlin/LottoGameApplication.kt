import controller.LottoGameController
import model.RandomNumbersGenerator
import util.ExceptionHandlerImpl
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        ExceptionHandlerImpl(),
        RandomNumbersGenerator(),
    ).startLottoGame()
}
