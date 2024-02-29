import controller.LottoGameController
import model.RandomNumbersGenerator
import util.ExceptionHandler
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        ExceptionHandler(),
        RandomNumbersGenerator(),
    ).startLottoGame()
}
