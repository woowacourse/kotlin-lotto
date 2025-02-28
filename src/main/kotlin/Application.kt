import controller.LottoController
import domain.service.LottoMatchCalculator
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoMatchCalculator = LottoMatchCalculator()
    val controller = LottoController(inputView, outputView, lottoMatchCalculator)

    controller.run()
}
