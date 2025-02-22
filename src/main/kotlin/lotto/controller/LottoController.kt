package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoDiscriminator
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoProfitCalculator
import lotto.model.ProfitStatus
import lotto.model.Rank
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun run() {
        outputView.printPurchaseAmountGuide()
        val purchaseAmount = inputView.readPurchaseAmount()
        val lottoCashier = LottoCashier(purchaseAmount)
        val lottoQuantity = lottoCashier.getLottoQuantity()
        outputView.printPurchaseLottoQuantity(lottoQuantity)

        val lottoMachine = LottoMachine()
        val lottos = lottoMachine.getLottos(lottoQuantity)

        lottos.lottos.forEach { lotto ->
            outputView.printLottoNumbers(lotto.numbers.map { number -> number.number })
        }

        outputView.printLastWeekWinningNumbersGuide()
        val lastWeekLottoWinningNumbers = Lotto.from(inputView.readWinningNumbers())

        outputView.printBonusNumberGuide()
        val lottoBonusNumber = LottoNumber(inputView.readBonusNumber())

        val lottoDiscriminator = LottoDiscriminator(lastWeekLottoWinningNumbers, lottoBonusNumber)

        val lottoWinningResult = lottos.countLottoByRank(lottoDiscriminator)

        outputView.printWinningResultTitle()
        lottoWinningResult.forEach { (rank, count) ->
            if (rank != Rank.MISS) {
                outputView.printWinningResult(
                    requiredMatch = rank.countOfMatch,
                    profit = rank.winningMoney,
                    matchBonus = rank.matchBonus,
                    countOfMatch = count,
                )
            }
        }

        val lottoProfitCalculator = LottoProfitCalculator()

        val profitRate =
            lottoProfitCalculator.getProfitRate(
                countLottoByRank = lottoWinningResult,
                purchaseAmount = lottoCashier.amount,
            )
        val profitStatus = ProfitStatus.fromProfitStatus(profitRate)

        outputView.printProfitRate(
            profitRate = profitRate,
            profitDescription = profitStatus.krDescription,
        )
    }
}
