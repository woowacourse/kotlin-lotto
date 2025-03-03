package lotto.controller

import lotto.model.AutoLottoGenerator
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoQuantity
import lotto.model.Lottos
import lotto.model.ManualLottoGenerator
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
        val allLottos = getAllLottos(lottoQuantity, LottoMachine(AutoLottoGenerator()))
        showPurchaseLottosInfo(lottoQuantity, allLottos)

        val winningNumbers = getWinningNumbers()
        val bonusNumber = getBonusNumber()

        showTotalResult(allLottos, winningNumbers, bonusNumber, lottoQuantity)
    }

    private fun getWinningNumbers(): Set<LottoNumber> =
        runCatching {
            inputView.readWinningNumbers().mapToLottoNumbers()
        }.getOrElse { error ->
            println(error.message)
            getWinningNumbers()
        }

    private fun getBonusNumber(): LottoNumber =
        runCatching {
            inputView.readBonusNumber().mapToLottoNumber()
        }.getOrElse { error ->
            println(error.message)
            getBonusNumber()
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
        val amount = getAmount()
        val manualLottoQuantity = getManualLottoQuantity()
        val lottoQuantity = LottoQuantity(amount, manualLottoQuantity)
        return lottoQuantity
    }

    private fun getAmount(): Int =
        runCatching {
            inputView.readPurchaseAmount()
        }.getOrElse { error ->
            println(error.message)
            getAmount()
        }

    private fun getManualLottoQuantity(): Int =
        kotlin
            .runCatching {
                inputView.readManualPurchaseQuantity()
            }.getOrElse { error ->
                println(error)
                getManualLottoQuantity()
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
            lottoMachine.generateLotto()
        }

    private fun getManualLottos(manualLottoQuantity: Int): List<Lotto> {
        val manualLottoNumbers = inputView.readManualLottoNumbers(manualLottoQuantity)
        return manualLottoNumbers.map {
            LottoMachine(ManualLottoGenerator(it)).generateLotto()
        }
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
