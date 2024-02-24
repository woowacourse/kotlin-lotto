import lotto.controller.LottoController
import lotto.domain.Cashier
import lotto.domain.LottoDrawingMachine
import lotto.domain.RandomLottoGenerator

fun main() {
    val randomLottoGenerator = RandomLottoGenerator()
    val controller = LottoController(Cashier(), randomLottoGenerator, LottoDrawingMachine())
    controller.start()
}
