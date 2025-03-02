package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoQuantity
import lotto.model.Lottos
import lotto.model.ProfitStatus
import lotto.model.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val amount = inputView.readPurchaseAmount()
        val passiveLottoQuantity = inputView.readPassivePurchaseQuantity()
        val passiveNumbers = inputView.readPassiveLottoNumbers(passiveLottoQuantity)
        val passiveLottos = passiveNumbers.map { Lotto(it.map { number -> LottoNumber(number) }.toSet()) }

        val lottoQuantity = LottoQuantity(amount, passiveLottoQuantity)
        val lottoMachine = LottoMachine(lottoQuantity)

        outputView.printPurchaseLottoQuantity(passiveLottoQuantity, lottoQuantity.getActiveLottoQuantity())

        val activeLottos = lottoMachine.generateActiveLottos()
        val lottos = Lottos(passiveLottos, activeLottos)
        val allLottos = lottos.getAllLottoNumbers()
        allLottos.forEach { lotto ->
            outputView.printLottoNumbers(lotto)
        }

        val winningNumbers = inputView.readWinningNumbers().mapToLotto()
        val bonusNumber = inputView.readBonusNumber().mapToLottoNumber()

        outputView.printWinningResultTitle()
        val winningResult = WinningResult(lottos, amount)
        val countLottoByRank = winningResult.countLottoByRank(winningNumbers, bonusNumber)
        countLottoByRank.forEach { (rank, count) ->
            outputView.printWinningResult(
                requiredMatch = rank.countOfMatch,
                profit = rank.winningMoney,
                matchBonus = rank.matchBonus,
                countOfMatch = count,
            )
        }

        val profitRate = winningResult.getProfitRate(winningNumbers, bonusNumber)
        val profitStatus = ProfitStatus.fromProfitStatus(profitRate)
        outputView.printProfitRate(profitRate, profitStatus.krDescription)
    }

    private fun Int.mapToLottoNumber() = LottoNumber(this)

    private fun Set<Int>.mapToLotto() = this.map { number -> number.mapToLottoNumber() }.toSet()
}
