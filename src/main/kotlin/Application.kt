import controller.LottoController
import domain.service.AutoLottoGenerator
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val autoLottoGenerator = AutoLottoGenerator()
    val controller = LottoController(inputView, outputView, autoLottoGenerator)

    controller.run()
}
