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
        val purchaseAmount = inputView.inputPurchase() ?: return outputView.printError("구입 금액 오류입니다.")
        val totalCount = purchaseAmount / 1000

        val manualCount = inputView.inputManualCount(totalCount) ?: return outputView.printError("수동 로또 개수 오류입니다.")

        val manualNumbersList = inputView.inputManualLottoNumber(manualCount)

        val manualLottoGenerator = ManualLottoGenerator(manualNumbersList)
        val manualLottos = manualLottoGenerator.generate(manualCount)

        val autoLottos = randomLottoGenerator.generate(totalCount - manualCount)

        outputView.printLottoCounts(manualCount, totalCount - manualCount)
        outputView.printAllLottos(manualLottos + autoLottos)

        // 당첨 번호 입력 및 검증 후 생성
        val winningNumbers = inputView.inputWinningNumbers() ?: return outputView.printError("당첨 번호 오류입니다.")
        val bonusNumberInput = inputView.inputBonusNumber() ?: return outputView.printError("보너스 번호 오류입니다.")

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
