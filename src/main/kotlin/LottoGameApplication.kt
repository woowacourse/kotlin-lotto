import controller.LottoGameController
import model.DefaultAutoLottieGenerator
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        DefaultAutoLottieGenerator(),
    ).startLottoGame()
}
