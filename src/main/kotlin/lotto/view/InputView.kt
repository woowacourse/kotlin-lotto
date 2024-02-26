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

    fun getWinningNumbers(): Set<LottoNumber> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val input = readln()
        input.split(",").forEach { ValidationUtils.validateLottoNumber(it.trim()) }
        return input.split(",").map { LottoNumber.valueOf(it.trim().toInt()) }.toSet()
    }

    fun getBonusNumber(): LottoNumber {
        println("보너스 번호를 입력해 주세요.")
        val input = readln()
        ValidationUtils.validateLottoNumber(input)
        return LottoNumber.valueOf(input.toInt())
    }
}
