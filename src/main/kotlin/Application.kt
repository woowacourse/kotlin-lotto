import controller.LottoController
import view.ConsoleInputView
import view.ConsoleOutputView
import view.InputView
import view.OutputView

fun main() {
    val inputView: InputView = ConsoleInputView()
    val outputView: OutputView = ConsoleOutputView()
    val lottoController = LottoController(inputView, outputView)
    lottoController.run()
}
