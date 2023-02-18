import domain.LottoAdministrator
import domain.LottoMachine
import domain.ShuffledNumbersGenerator
import model.LottoResult
import view.InputView
import view.OutputView

fun main() = LottoController(
    InputView(),
    OutputView(),
    LottoMachine(ShuffledNumbersGenerator()),
    LottoAdministrator(),
    LottoResult(),
).run()
