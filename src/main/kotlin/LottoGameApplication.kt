import controller.LottoGameController
import model.AutoLottoGenerator
import model.RandomLottoGameManager
import util.ExceptionHandlerImpl
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        ExceptionHandlerImpl(),
        RandomLottoGameManager(AutoLottoGenerator()),
    ).startLottoGame()
}
