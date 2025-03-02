package lotto.controller

import lotto.model.Lotto
import lotto.model.LottoGenerator
import lotto.model.LottoNumber
import lotto.model.LottoStatisticResult
import lotto.model.ManualLottoGenerator
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

        val manualLottoGenerator = ManualLottoGenerator(manualNumbersList)
        val manualLottos = manualLottoGenerator.generate(manualCount)

        val autoLottos = randomLottoGenerator.generate(totalCount - manualCount)

        outputView.printLottoCounts(manualCount, totalCount - manualCount)
        outputView.printAllLottos(manualLottos + autoLottos)

        val winningNumbers = inputView.inputWinningNumbers()
        val bonusNumberInput = inputView.inputBonusNumber()

        val winningLotto =
            WinningLotto(
                lotto = Lotto.from(winningNumbers) ?: return outputView.printError("당첨 로또 생성에 실패했습니다."),
                bonusNumber = LottoNumber.from(bonusNumberInput),
            )

        val matchResults =
            (manualLottos + autoLottos)
                .groupingBy { it.match(winningLotto) }
                .eachCount()

        val profitRate = LottoStatisticResult().calculateProfit(purchaseAmount, matchResults)

        outputView.printResult(matchResults, profitRate)
    }
}
