import controller.LottoGameController
import model.AutoLottoGenerator
import util.ExceptionHandlerImpl
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        AutoLottoGenerator(),
        ExceptionHandlerImpl(),
    ).startLottoGame()
}
