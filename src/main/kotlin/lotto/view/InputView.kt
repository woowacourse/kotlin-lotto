package lotto.view

import lotto.util.ValidationUtils

object InputView {
    fun getPurchaseAmount(): Int {
        println("구입금액을 입력해 주세요.")
        val input = readln()
        return input.toIntOrNull() ?: -1
    }

    fun getWinningNumbers(): Set<Int> {
        println("지난 주 당첨 번호를 입력해 주세요.")
        val input = readln()
        input.split(",").forEach { ValidationUtils.validateLottoNumber(it.trim()) }
        return input.split(",").map { it.trim().toIntOrNull() ?: -1 }.toSet()
    }

    fun getBonusNumber(): Int {
        println("보너스 번호를 입력해 주세요.")
        val input = readln()
        return input.toIntOrNull() ?: -1
    }

    fun getNumberOfManualLotto(): Int {
        println("수동으로 구매할 로또 수를 입력해 주세요.")
        return readln().toIntOrNull() ?: -1
    }

    fun getManualLottoNumbers(count: Int): List<Set<Int>> {
        println("수동으로 구매할 번호를 입력해 주세요.")
        return List(count) {
            val input = readln().split(",")
            input.forEach { ValidationUtils.validateLottoNumber(it.trim()) }
            input.map { it.trim().toIntOrNull() ?: -1 }.toSet()
        }
    }
}
