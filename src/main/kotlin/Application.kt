import domain.LottoAdministrator
import domain.LottoMachine
import domain.ShuffledNumberGenerator
import model.LottoResult
import view.InputView
import view.OutputView

fun main() = LottoController(
    InputView(),
    OutputView(),
    LottoMachine(ShuffledNumberGenerator()),
    LottoAdministrator(),
    LottoResult(),
).run()
