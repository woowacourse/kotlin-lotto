package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoCashier
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import lotto.model.LottoProfitCalculator
import lotto.model.LottoRankDiscriminator
import lotto.model.Lottos
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
        val lottoCashier = readyMoneyToPurchaseLotto()

        val lottoQuantity = determineLottoQuantity(lottoCashier)
        outputView.printPurchaseLottoQuantity(lottoQuantity)

        val lottoMachine = LottoMachine()
        val lottos = loadAndDisplayLottos(lottoMachine, lottoQuantity)

        outputView.printLastWeekWinningNumbersGuide()
        val lastWeekLottoWinningNumbers = setupLastWeekWinningNumber()

        outputView.printBonusNumberGuide()
        val lottoBonusNumber = setupLastWeekBonusNumber()

        val lottoRankDiscriminator = LottoRankDiscriminator(lastWeekLottoWinningNumbers, lottoBonusNumber)
        val lottoWinningResult = lottos.countLottoByRank(lottoRankDiscriminator)

        displayLottosResult(lottoWinningResult)
        calculateAndDisplayProfitRate(lottoWinningResult, lottoCashier)
    }

    private fun readyMoneyToPurchaseLotto(): LottoCashier {
        val purchaseAmount = inputView.readPurchaseAmount()
        val lottoCashier = LottoCashier(purchaseAmount)
        return lottoCashier
    }

    private fun determineLottoQuantity(lottoCashier: LottoCashier): Int {
        val lottoQuantity = lottoCashier.getPurchaseQuantity()
        return lottoQuantity
    }

    private fun loadAndDisplayLottos(
        lottoMachine: LottoMachine,
        lottoQuantity: Int,
    ): Lottos {
        val lottos = lottoMachine.getLottos(lottoQuantity)

        lottos.lottos.forEach { lotto ->
            outputView.printLottoNumbers(lotto.numbers.map { it.number })
        }

        return lottos
    }

    private fun setupLastWeekWinningNumber(): Lotto {
        val lastWeekLottoWinningNumbers = Lotto.from(inputView.readWinningNumbers())
        return lastWeekLottoWinningNumbers
    }

    private fun setupLastWeekBonusNumber(): LottoNumber {
        val lottoBonusNumber = LottoNumber.from(inputView.readBonusNumber())
        return lottoBonusNumber
    }

    private fun displayLottosResult(lottoWinningResult: Map<Rank, Int>) {
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
    }

    private fun calculateAndDisplayProfitRate(
        lottoWinningResult: Map<Rank, Int>,
        lottoCashier: LottoCashier,
    ) {
        val lottoProfitCalculator = LottoProfitCalculator()
        val profitRate =
            lottoProfitCalculator.getProfitRate(
                countLottoByRank = lottoWinningResult,
                purchaseAmount = lottoCashier.amount,
            )
        val profitStatus = ProfitStatus.from(profitRate)

        outputView.printProfitRate(
            profitRate = profitRate,
            profitDescription = profitStatus.krDescription,
        )
    }
}
