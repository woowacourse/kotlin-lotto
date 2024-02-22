import controller.LottoGameController
import model.AutoLottoGenerator
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        AutoLottoGenerator(),
    ).startLottoGame()
}
