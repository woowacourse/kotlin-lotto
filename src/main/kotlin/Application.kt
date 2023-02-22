import controller.LottoGameController
import domain.game.LottoGame
import domain.game.LottoMachine
import util.validator.InputValidator
import view.InputView
import view.ResultView

fun main() {
    val inputView = InputView(InputValidator())
    val resultView = ResultView()
    val lottoGame = LottoGame(LottoMachine())

    val lottoGameController = LottoGameController(inputView, resultView, lottoGame)
    lottoGameController.startLottoGame()
}
