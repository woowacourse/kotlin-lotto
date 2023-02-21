package view

import domain.Lotto
import domain.LottoNumber
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney

class InputView : InputViewInterface {
    override fun getMoney(): PurchaseLottoMoney {
        while (true) {
            println(INPUT_MESSAGE_FOR_PURCHASE_MONEY)
            val money = validateMoney(readln().toIntOrNull()) ?: continue
            runCatching { PurchaseLottoMoney(money) }
                .onSuccess {
                    println()
                    return it
                }
                .onFailure { println(it.message) }
        }
    }

    private fun validateMoney(money: Int?): Int? {
        if (money == null) {
            println(ERROR_MONEY_TYPE_CONVERT)
            return null
        }
        return money
    }

    override fun getManualCount(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo {
        println(INPUT_MESSAGE_MANUAL_LOTTO_COUNT)
        while (true) {
            val manualCount = validateManualCount(readln().toIntOrNull()) ?: continue
            runCatching { LottoPurchaseInfo(purchaseLottoMoney, manualCount) }
                .onSuccess {
                    println()
                    return it
                }
                .onFailure { println(it.message) }
        }
    }

    private fun validateManualCount(count: Int?): Int? {
        if (count == null) {
            println(ERROR_MANUAL_COUNT_TYPE_CONVERT)
            return null
        }
        return count
    }

    override fun getManualNumbers(purchaseInfo: LottoPurchaseInfo): List<Lotto> {
        println(INPUT_MESSAGE_MANUAL_LOTTO_NUMBERS)
        val lottos = mutableListOf<Lotto>()
        repeat(purchaseInfo.manualCount) { lottos.add(getManualNumber()) }
        println()
        return lottos
    }

    private fun getManualNumber(): Lotto {
        while (true) {
            val lotto = validateManualNumber(readln().split(",").map { it.trim().toIntOrNull() }) ?: continue
            runCatching { Lotto(lotto.map { LottoNumber.from(it) }.toSet()) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    private fun validateManualNumber(lotto: List<Int?>): List<Int>? {
        if (lotto.contains(null)) {
            println(ERROR_MANUAL_NUMBERS_FORMAT_IS_INCORRECT)
            return null
        }
        return lotto.map { it!! }
    }

    override fun getWinningNumbers(): Lotto {
        println(INPUT_MESSAGE_FOR_WINNING_NUMBERS)
        while (true) {
            val numbers = validateWinningNumbers(readln().split(",").map { it.trim().toIntOrNull() }) ?: continue
            runCatching { Lotto(numbers.map { LottoNumber.from(it) }.toSet()) }
                .onSuccess {
                    println()
                    return it
                }
                .onFailure { println(it.message) }
        }
    }

    private fun validateWinningNumbers(numbers: List<Int?>): List<Int>? {
        if (numbers.contains(null)) {
            println(ERROR_MANUAL_NUMBERS_FORMAT_IS_INCORRECT)
            return null
        }
        return numbers.map { it!! }
    }

    override fun getBonusNumber(): LottoNumber {
        println(INPUT_MESSAGE_FOR_BONUS_NUMBER)
        while (true) {
            val bonusNumber = validateBonusNumber(readln().toIntOrNull()) ?: continue
            runCatching { LottoNumber.from(bonusNumber) }
                .onSuccess {
                    println()
                    return it
                }
                .onFailure { println(it.message) }
        }
    }

    private fun validateBonusNumber(bonusNumber: Int?): Int? {
        if (bonusNumber == null) {
            println(ERROR_BONUS_NUMBER_TYPE_CONVERT)
            return null
        }
        return bonusNumber
    }

    companion object {
        private const val INPUT_MESSAGE_FOR_PURCHASE_MONEY = "구입 금액을 입력해 주세요."
        private const val INPUT_MESSAGE_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_MESSAGE_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MESSAGE_FOR_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_MESSAGE_FOR_BONUS_NUMBER = "보너스 볼을 입력해 주세요."

        private const val ERROR_MONEY_TYPE_CONVERT = "[ERROR] 금액을 숫자로 다시 입력해주세요."
        private const val ERROR_MANUAL_COUNT_TYPE_CONVERT = "[ERROR] 수동 발급 개수를 숫자로 다시 입력해주세요."
        private const val ERROR_MANUAL_NUMBERS_FORMAT_IS_INCORRECT = "[ERROR] 콤마로 숫자를 구분해서 다시 입력해주세요."
        private const val ERROR_BONUS_NUMBER_TYPE_CONVERT = "[ERROR] 보너스 넘버를 숫자로 다시 입력해주세요."
    }
}
