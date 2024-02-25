import controller.LottoController
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoController = LottoController(inputView, outputView)

    lottoController.start()
}
