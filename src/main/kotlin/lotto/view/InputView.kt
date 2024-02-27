package lotto.view

import lotto.model.LottoNumber
import lotto.util.ValidationUtils

object InputView {
    fun getPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        ValidationUtils.validatePurchaseAmount(input)
        return input.toInt()
    }

    fun getManualTicketCounts(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        val input = readln()
        ValidationUtils.validateManualCounts(input)
        return input.toInt()
    }

    fun getWinningNumbers(): List<LottoNumber> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val input = readln()
        return input.split(",").map { LottoNumber(it.trim()) }
    }

    fun getBonusNumber(): LottoNumber {
        println("보너스 번호를 입력해 주세요.")
        val input = readln()
        return LottoNumber(input)
    }
}
