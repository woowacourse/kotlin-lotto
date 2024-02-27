import controller.LottoGameController
import model.DefaultAutoLottieGenerator
import model.DefaultLottoMachine
import model.DefaultManualLottieGenerator
import model.Money
import view.ConsoleLottoGameInputView
import view.ConsoleLottoGameOutputView

fun main() {
    LottoGameController(
        ConsoleLottoGameInputView(),
        ConsoleLottoGameOutputView(),
        DefaultLottoMachine(Money(1_000), DefaultAutoLottieGenerator(), DefaultManualLottieGenerator()),
    ).startLottoGame()
}
