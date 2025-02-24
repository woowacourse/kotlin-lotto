package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoProfitCalculator
import lotto.model.ProfitStatus
import lotto.model.Rank
import lotto.model.WinningDiscriminator
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()

        // TODO: 수동 입력 추가
        val lottos = getPurchaseLottos(emptyList(), purchaseAmount)

        outputView.printPurchaseLottoQuantity(lottos.size)
        lottos.forEach { lotto ->
            outputView.printLottoNumbers(lotto.numbers.map { it.number })
        }

        outputView.printWinningNumbersGuide()
        val winningNumbers = inputView.readWinningNumbers()

        outputView.printBonusNumberGuide()
        val bonusNumber = inputView.readBonusNumber()

        val lottoWinningResult = getLottosDiscriminateResult(lottos, winningNumbers, bonusNumber)

        outputView.printWinningResultTitle()
        lottoWinningResult.forEach { (rank, count) ->
            if (rank == Rank.MISS) return@forEach

            outputView.printWinningResult(
                requiredMatch = rank.countOfMatch,
                profit = rank.winningMoney,
                matchBonus = rank == Rank.SECOND,
                countOfMatch = count,
            )
        }

        val profitRate = getProfitRate(lottoWinningResult, purchaseAmount)
        val profitStatus = ProfitStatus.from(profitRate)

        outputView.printProfitRate(profitRate, profitStatus.krDescription)
    }

    private fun getPurchaseLottos(
        manualLottos: List<Lotto>,
        purchaseAmount: Int,
    ): List<Lotto> {
        val lottoCashier = LottoCashier(purchaseAmount)
        val lottoMachine = LottoMachine()

        return lottoMachine.getTotalLottos(manualLottos, lottoCashier.getPurchaseQuantity())
    }

    private fun getLottosDiscriminateResult(
        lottos: List<Lotto>,
        winningNumbers: List<Int>,
        bonusNumber: Int,
    ): Map<Rank, Int> {
        val winningLotto = Lotto.from(winningNumbers)
        val bonusLottoNumber = LottoNumber.from(bonusNumber)
        val winningDiscriminator = WinningDiscriminator(winningLotto, bonusLottoNumber)

        return winningDiscriminator.getResult(lottos)
    }

    private fun getProfitRate(
        lottoWinningResult: Map<Rank, Int>,
        purchaseAmount: Int,
    ): Float = LottoProfitCalculator().getProfitRate(lottoWinningResult, purchaseAmount)
}
