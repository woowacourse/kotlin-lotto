package lotto.view

import lotto.model.LottoNumber
import lotto.model.LottoTicket
import lotto.model.Rank
import java.text.DecimalFormat

class UserInterface(
    private val inputValidator: InputValidator = InputValidator(),
) {
    fun inputPurchaseAmount(): Int {
        OutputView.printMessage("구입금액을 입력해 주세요.")
        val purchaseAmount = InputView.getUserInput()
        inputValidator.validateInteger(purchaseAmount)
        inputValidator.validateOverZero(purchaseAmount)
        return purchaseAmount.toInt()
    }

    fun printLottoCount(lottoCount: Int): Boolean {
        OutputView.printMessage("\n발행할 수 있는 로또 수는 ${lottoCount}입니다. ${lottoCount}만큼 발행하겠습니까? Y / N 로 대답해주세요.")
        val yesOrNo = InputView.getUserInput()
        return yesOrNo == "Y"
    }

    fun printChange(change: Int) {
        OutputView.printMessage("\n거스름 돈은 ${change}입니다.")
    }

    fun getManualLottoCount(): Int {
        OutputView.printMessage("\n수동으로 구매할 로또 수를 입력해 주세요.")
        val manualLottoCount = InputView.getUserInput()
        inputValidator.validateInteger(manualLottoCount)
        inputValidator.validateOverZero(manualLottoCount)
        return manualLottoCount.toInt()
    }


    fun getManualLottoNumbers(manualLottoCount: Int): List<List<Int>> {
        val manualLottoNumber = mutableListOf<List<Int>>()
        OutputView.printMessage("\n수동으로 구매할 번호를 입력해 주세요..")
        repeat(manualLottoCount) {
            val numbers = InputView.getUserInput()
            manualLottoNumber.add(numbers.split(",").map { it.trim().toInt() })
        }
        return manualLottoNumber
    }

    fun printLottoTickets(manualLottoCount : Int, autoLottoCount: Int, lottoTickets: List<LottoTicket>) {
        OutputView.printMessage("\n수동으로 ${manualLottoCount}장, 자동으로 ${autoLottoCount}개를 구매했습니다.")
        lottoTickets.map {
            OutputView.printMessage(it.getNumbers().map { it.number }.joinToString(", ", "[", "]"))
        }
    }

    fun getWinningNumbers(): List<LottoNumber> {
        OutputView.printMessage("\n지난 주 당첨 번호를 입력해 주세요.")
        val winningNumbers = InputView.getUserInput().split(",").map { it.trim() }
        for (winningNumber in winningNumbers) {
            inputValidator.validateInteger(winningNumber)
        }
        return winningNumbers.map { LottoNumber(it.toInt()) }
    }

    fun getBonusNumber(): LottoNumber {
        OutputView.printMessage("보너스 볼을 입력해 주세요.")
        val bonusNumber = InputView.getUserInput()
        inputValidator.validateInteger(bonusNumber)
        return LottoNumber(bonusNumber.toInt())
    }

    fun printResult(results: Map<Rank, Int>) {
        println("\n당첨 통계")
        println("---------")

        val rankOrder = listOf(Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.FIRST)

        val rankMessages =
            mapOf(
                Rank.FIFTH to "3개 일치",
                Rank.FOURTH to "4개 일치",
                Rank.THIRD to "5개 일치",
                Rank.SECOND to "5개 일치, 보너스 볼 일치",
                Rank.FIRST to "6개 일치",
            )

        val prizeMessages =
            mapOf(
                Rank.FIFTH to "5000원",
                Rank.FOURTH to "50000원",
                Rank.THIRD to "1500000원",
                Rank.SECOND to "30000000원",
                Rank.FIRST to "2000000000원",
            )
        for (rank in rankOrder) {
            val count = results.getOrDefault(rank, 0)
            println("${rankMessages[rank]} (${prizeMessages[rank]}) - ${count}개")
        }
    }

    fun printProfit(profit: Double) {
        val df = DecimalFormat("#.##")
        OutputView.printMessage("총 수익률은 ${df.format(profit)}입니다.")
    }
}
