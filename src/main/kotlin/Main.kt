import controller.LottoController
import domain.service.LottoGenerator
import domain.service.LottoGeneratorImpl
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val lottoGenerator: LottoGenerator = LottoGeneratorImpl()
    val controller = LottoController(inputView, outputView, lottoGenerator)

    controller.run()
}
