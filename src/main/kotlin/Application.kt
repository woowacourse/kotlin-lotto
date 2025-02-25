import controller.LottoController
import domain.service.AutoLottoGenerator
import domain.service.LottoMatchCalculator
import domain.service.ManualLottoGenerator
import view.InputView
import view.OutputView

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val autoLottoGenerator = AutoLottoGenerator()
    val manualLottoGenerator = ManualLottoGenerator()
    val lottoMatchCalculator = LottoMatchCalculator()
    val controller =
        LottoController(inputView, outputView, autoLottoGenerator, manualLottoGenerator, lottoMatchCalculator)

    controller.run()
}
