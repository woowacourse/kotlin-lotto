import controller.LottoController
import view.InputView
import view.OutputView

fun main() {
    val lottoController = LottoController(InputView(), OutputView())
    lottoController.run()
}
