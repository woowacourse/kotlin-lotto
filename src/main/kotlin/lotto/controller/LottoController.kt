package lotto.controller

import lotto.model.ProfitStatus
import lotto.model.Rank
import lotto.service.LottoService
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val lottoService: LottoService,
) {
    fun run() {
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()
        val lottos = lottoService.getPurchaseLottos(purchaseAmount)

        outputView.printPurchaseLottoQuantity(lottos.lottos.size)
        lottos.lottos.forEach { lotto ->
            outputView.printLottoNumbers(lotto.numbers.map { it.number })
        }

        outputView.printWinningNumbersGuide()
        val winningNumbers = inputView.readWinningNumbers()

        outputView.printBonusNumberGuide()
        val bonusNumber = inputView.readBonusNumber()

        val lottoWinningResult = lottoService.getLottosDiscriminateResult(lottos, winningNumbers, bonusNumber)

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

        val profitRate = lottoService.getProfitRate(lottoWinningResult, purchaseAmount)
        val profitStatus = ProfitStatus.from(profitRate)

        outputView.printProfitRate(profitRate, profitStatus.krDescription)
    }
}
