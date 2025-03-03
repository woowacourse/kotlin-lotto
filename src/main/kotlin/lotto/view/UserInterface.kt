package lotto.view

import lotto.model.LottoNumber
import lotto.model.LottoTicket
import lotto.model.Rank
import java.text.DecimalFormat

class UserInterface(
    private val inputValidator: InputValidator = InputValidator(),
) {
    fun inputPurchaseAmount(): Int {
        OutputView.printlnMessage(PURCHASE_AMOUNT_PROMPT)
        val purchaseAmount = InputView.getUserInput()
        inputValidator.validateInteger(purchaseAmount)
        inputValidator.validateOverZero(purchaseAmount)
        return purchaseAmount.toInt()
    }

    fun printLottoCount(lottoCount: Int): Boolean {
        OutputView.printlnMessage(String.format(LOTTO_COUNT_PROMPT, lottoCount, lottoCount))
        val yesOrNo = InputView.getUserInput()
        return yesOrNo == "Y"
    }

    fun printChange(change: Int) {
        OutputView.printlnMessage(String.format(CHANGE_MESSAGE, change))
    }

    fun getManualLottoCount(): Int {
        OutputView.printlnMessage(MANUAL_LOTTO_COUNT_PROMPT)
        val manualLottoCount = InputView.getUserInput()
        inputValidator.validateInteger(manualLottoCount)
        inputValidator.validateNoNegativeNumber(manualLottoCount)
        return manualLottoCount.toInt()
    }

    fun getManualLottoNumbers(manualLottoCount: Int): List<List<Int>> {
        val manualLottoNumber = mutableListOf<List<Int>>()
        OutputView.printlnMessage(MANUAL_LOTTO_NUMBERS_PROMPT)
        OutputView.printlnMessage(MANUAL_LOTTO_FORMAT_PROMPT)
        repeat(manualLottoCount) {
            val numbers = InputView.getUserInput()
            manualLottoNumber.add(numbers.split(",").map { it.trim().toInt() })
        }
        return manualLottoNumber
    }

    fun printLottoTickets(
        manualLottoCount: Int,
        autoLottoCount: Int,
        lottoTickets: List<LottoTicket>,
    ) {
        OutputView.printlnMessage(String.format(LOTTO_PURCHASE_RESULT_MESSAGE, manualLottoCount, autoLottoCount))
        lottoTickets.map {
            OutputView.printMessage("${it.lottoIssueType.issueType} ")
            OutputView.printMessage(it.getNumbers().map { it.number }.joinToString(", ", "[", "]"))
        }
    }

    fun getWinningNumbers(): List<LottoNumber> {
        OutputView.printlnMessage(WINNING_NUMBERS_PROMPT)
        val winningNumbers = InputView.getUserInput().split(",").map { it.trim() }
        for (winningNumber in winningNumbers) {
            inputValidator.validateInteger(winningNumber)
        }

        return winningNumbers.map { LottoNumber(it.toInt()) }
    }

    fun getBonusNumber(): LottoNumber {
        OutputView.printlnMessage(BONUS_NUMBER_PROMPT)
        val bonusNumber = InputView.getUserInput()
        inputValidator.validateInteger(bonusNumber)
        return LottoNumber(bonusNumber.toInt())
    }

    fun printResult(results: Map<Rank, Int>) {
        OutputView.printlnMessage(WINNING_STATISTICS_HEADER)
        OutputView.printlnMessage(WINNING_STATISTICS_DIVIDER)

        val rankOrder = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)

        val rankMessages =
            mapOf(
                Rank.FIFTH to RANK_3_MATCH,
                Rank.FOURTH to RANK_4_MATCH,
                Rank.THIRD to RANK_5_MATCH,
                Rank.SECOND to RANK_5_MATCH_BONUS,
                Rank.FIRST to RANK_6_MATCH,
            )

        val prizeMessages =
            mapOf(
                Rank.FIFTH to PRIZE_5000,
                Rank.FOURTH to PRIZE_50000,
                Rank.THIRD to PRIZE_1500000,
                Rank.SECOND to PRIZE_30000000,
                Rank.FIRST to PRIZE_2000000000,
            )
        for (rank in rankOrder) {
            val count = results.getOrDefault(rank, 0)
            OutputView.printlnMessage("${rankMessages[rank]} (${prizeMessages[rank]}) - ${count}개")
        }
    }

    fun printProfit(profit: Double) {
        val df = DecimalFormat("#.##")
        OutputView.printlnMessage(String.format(PROFIT_MESSAGE, df.format(profit)))
    }

    companion object {
        private const val PURCHASE_AMOUNT_PROMPT = "구입금액을 입력해 주세요."
        private const val LOTTO_COUNT_PROMPT = "\n발행할 수 있는 로또 수는 %d입니다. %d만큼 발행하겠습니까? Y / N 로 대답해주세요."
        private const val CHANGE_MESSAGE = "\n거스름 돈은 %d원입니다."
        private const val MANUAL_LOTTO_COUNT_PROMPT = "\n수동으로 구매할 로또 수를 입력해 주세요."
        private const val MANUAL_LOTTO_NUMBERS_PROMPT = "\n수동으로 구매할 번호를 입력해 주세요."
        private const val MANUAL_LOTTO_FORMAT_PROMPT = "수동으로 구매할 번호는 쉼표로 구분된 숫자로 입력해 주세요."
        private const val WINNING_NUMBERS_PROMPT = "\n지난 주 당첨 번호를 입력해 주세요."
        private const val BONUS_NUMBER_PROMPT = "보너스 볼을 입력해 주세요."
        private const val LOTTO_PURCHASE_RESULT_MESSAGE = "\n수동으로 %d장, 자동으로 %d개를 구매했습니다."
        private const val WINNING_STATISTICS_HEADER = "\n당첨 통계"
        private const val WINNING_STATISTICS_DIVIDER = "---------"
        private const val PRIZE_5000 = "5000원"
        private const val PRIZE_50000 = "50000원"
        private const val PRIZE_1500000 = "1500000원"
        private const val PRIZE_30000000 = "30000000원"
        private const val PRIZE_2000000000 = "2000000000원"
        private const val RANK_3_MATCH = "3개 일치"
        private const val RANK_4_MATCH = "4개 일치"
        private const val RANK_5_MATCH = "5개 일치"
        private const val RANK_5_MATCH_BONUS = "5개 일치, 보너스 볼 일치"
        private const val RANK_6_MATCH = "6개 일치"
        private const val PROFIT_MESSAGE = "총 수익률은 %s입니다."
    }
}
