package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoFactory
import lotto.model.LottoGenerator
import lotto.model.LottoNumber
import lotto.model.LottoStatisticResult
import lotto.model.WinningLotto
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView,
    private val outputView: OutputView,
    private val randomLottoGenerator: LottoGenerator,
) {
    fun play() {
        val purchaseAmount = inputView.inputPurchase()
        val totalCount = purchaseAmount / 1000

        val manualCount = inputView.inputManualCount(totalCount)

        val manualNumbersList = inputView.inputManualLottoNumber(manualCount)

        val lottoFactory = LottoFactory(randomLottoGenerator, manualNumbersList)
        val allLottos = lottoFactory.createLottos(totalCount)

        outputView.printLottoCounts(manualCount, totalCount - manualCount)
        outputView.printAllLottos(allLottos)

        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumberInput = inputView.inputBonusNumber()

        val winningLotto =
            WinningLotto(
                lotto = Lotto.from(winningNumbers) ?: return outputView.printError("당첨 로또 생성에 실패했습니다."),
                bonusNumber = LottoNumber.from(bonusNumberInput),
            )

        val matchResults =
            allLottos
                .groupingBy { winningLotto.match(it) }
                .eachCount()

        val profitRate = LottoStatisticResult().calculateProfit(purchaseAmount, matchResults)

        outputView.printResult(matchResults, profitRate)
    }
}
