package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoQuantity
import lotto.model.Lottos
import lotto.model.ProfitStatus
import lotto.model.Rank
import lotto.model.WinningResult
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        val lottoQuantity = getLottoQuantity()
        val allLottos = getAllLottos(lottoQuantity, LottoMachine())
        showPurchaseLottosInfo(lottoQuantity, allLottos)

        val winningNumbers = inputView.readWinningNumbers().mapToLottoNumbers()
        val bonusNumber = inputView.readBonusNumber().mapToLottoNumber()

        showTotalResult(allLottos, winningNumbers, bonusNumber, lottoQuantity)
    }

    private fun showTotalResult(
        allLottos: Lottos,
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
        lottoQuantity: LottoQuantity,
    ) {
        val winningResult = WinningResult(allLottos)
        val countLottoByRank = winningResult.countLottoByRank(winningNumbers, bonusNumber)
        showWinningResult(countLottoByRank)
        showProfit(winningResult, winningNumbers, bonusNumber, lottoQuantity)
    }

    private fun showPurchaseLottosInfo(
        lottoQuantity: LottoQuantity,
        allLottos: Lottos,
    ) {
        outputView.printPurchaseLottoQuantity(
            lottoQuantity.manualLottoQuantity,
            lottoQuantity.getAutoLottoQuantity(),
        )
        showLottos(allLottos)
    }

    private fun getLottoQuantity(): LottoQuantity {
        val amount = inputView.readPurchaseAmount()
        val manualLottoQuantity = inputView.readManualPurchaseQuantity()
        val lottoQuantity = LottoQuantity(amount, manualLottoQuantity)
        return lottoQuantity
    }

    private fun getAllLottos(
        lottoQuantity: LottoQuantity,
        lottoMachine: LottoMachine,
    ): Lottos {
        val manualLottos = getManualLottos(lottoQuantity.manualLottoQuantity)
        val autoLottos = generateAutoLottos(lottoQuantity, lottoMachine)
        val allLottos = Lottos(manualLottos, autoLottos)

        return allLottos
    }

    private fun generateAutoLottos(
        lottoQuantity: LottoQuantity,
        lottoMachine: LottoMachine,
    ): List<Lotto> =
        List(lottoQuantity.getAutoLottoQuantity()) {
            lottoMachine.getLottoNumbers()
        }

    private fun getManualLottos(manualLottoQuantity: Int): List<Lotto> {
        val manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoQuantity)
        val manualLottos = manualLottoNumbers.map { Lotto(it.mapToLottoNumbers()) }
        return manualLottos
    }

    private fun showLottos(lottos: Lottos) {
        val allLottoNumbers = lottos.getAllLottoNumbers()
        allLottoNumbers.forEach { lotto ->
            outputView.printLottoNumbers(lotto)
        }
    }

    private fun showWinningResult(countLottoByRank: Map<Rank, Int>) {
        outputView.printWinningResultTitle()
        countLottoByRank.forEach { (rank, count) ->
            outputView.printWinningResult(
                requiredMatch = rank.countOfMatch,
                profit = rank.winningMoney,
                matchBonus = rank.matchBonus,
                countOfMatch = count,
            )
        }
    }

    private fun showProfit(
        winningResult: WinningResult,
        winningNumbers: Set<LottoNumber>,
        bonusNumber: LottoNumber,
        lottoQuantity: LottoQuantity,
    ) {
        val profitRate = winningResult.getProfitRate(winningNumbers, bonusNumber, lottoQuantity)
        val profitStatus = ProfitStatus.fromProfitStatus(profitRate)
        outputView.printProfitRate(profitRate, profitStatus.krDescription)
    }

    private fun Int.mapToLottoNumber() = LottoNumber(this)

    private fun Set<Int>.mapToLottoNumbers() = this.map { number -> number.mapToLottoNumber() }.toSet()
}
