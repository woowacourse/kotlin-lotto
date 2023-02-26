import domain.LottoMachine
import domain.ShuffledLottoGenerator
import view.InputView
import view.OutputView

fun main() = LottoController(
    InputView(),
    OutputView(),
    LottoMachine(ShuffledLottoGenerator()),
).run()
