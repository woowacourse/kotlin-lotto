import model.domain.AutoLottoGenerator
import model.domain.LottoMachine
import model.domain.ManualLottoGenerator
import presentation.OrderLottoView

fun main() = Controller(
    OrderLottoView(),
    LottoMachine(),
    AutoLottoGenerator(),
    ManualLottoGenerator(),
).run()
