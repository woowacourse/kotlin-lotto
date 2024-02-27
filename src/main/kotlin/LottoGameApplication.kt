import controller.LottoGameController
import model.DefaultAutoLottieGenerator
import model.DefaultLottoMachine
import model.DefaultManualLottieGenerator
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        DefaultLottoMachine(DefaultAutoLottieGenerator(), DefaultManualLottieGenerator()),
    ).startLottoGame()
}
