package lotto.controller

import lotto.model.Lotto
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
        // 구매금액 입력 및 검증
        val purchaseAmount = inputView.inputPurchase() ?: return outputView.printError("구입 금액 오류입니다.")
        val totalCount = purchaseAmount / 1000

        // 수동 로또 개수 입력 및 검증
        val manualCount = inputView.inputManualCount(totalCount) ?: return outputView.printError("수동 로또 개수 오류입니다.")

        // 수동 로또 번호 입력받기 (명확히 List<List<Int>> 형태)
        val manualNumbersList = inputView.inputManualLottoNumber(manualCount)

        // 수동 로또 생성 (명확히 List<Lotto> 형태로 변환)
        val manualLottos =
            manualNumbersList.map { numbers ->
                Lotto.from(numbers) ?: return outputView.printError("잘못된 수동 로또 번호입니다.")
            }

        // 자동 로또 생성
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

        // 결과 계산 및 출력 (정상적으로 동작)
        val matchResults =
            (manualLottos + autoLottos)
                .groupingBy { it.match(winningLotto) }
                .eachCount()

        val profitRate = LottoStatisticResult().calculateProfit(purchaseAmount, matchResults)

        outputView.printResult(matchResults, profitRate)
    }
}
