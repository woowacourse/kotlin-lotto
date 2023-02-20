import controller.LottoGameController
import domain.game.LottoGame
import domain.game.LottoMachine
import util.validator.InputValidator
import view.InputView
import view.ResultView

fun main() {
    val lottoGameController = LottoGameController(InputView(InputValidator()), ResultView(), LottoGame(LottoMachine()))
    lottoGameController.startLottoGame()
}
