import controller.LottoGameController
import model.AutoLottieGenerator
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        AutoLottieGenerator(),
    ).startLottoGame()
}
