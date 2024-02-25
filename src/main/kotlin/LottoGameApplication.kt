import controller.LottoGameController
import model.AutoLottoGenerator
import model.LottoGameManager
import util.ExceptionHandlerImpl
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        ExceptionHandlerImpl(),
        LottoGameManager(AutoLottoGenerator()),
    ).startLottoGame()
}
