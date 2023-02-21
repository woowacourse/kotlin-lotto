package view

import domain.Lotto
import domain.LottoNumber
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney

class InputView : InputViewInterface {
    override fun getMoney(): PurchaseLottoMoney {
        while (true) {
            println(INPUT_MESSAGE_FOR_PURCHASE_MONEY)
            val money = readln()
            runCatching { PurchaseLottoMoney(money.toInt()) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    override fun getManualCount(purchaseLottoMoney: PurchaseLottoMoney): LottoPurchaseInfo {
        while (true) {
            println(INPUT_MESSAGE_MANUAL_LOTTO_COUNT)
            val manualCount = readln().toIntOrNull() ?: Int.MAX_VALUE
            runCatching { LottoPurchaseInfo(purchaseLottoMoney, manualCount) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    override fun getManualNumbers(purchaseInfo: LottoPurchaseInfo): List<Lotto> {
        println(INPUT_MESSAGE_MANUAL_LOTTO_NUMBERS)
        val lottos = mutableListOf<Lotto>()
        repeat(purchaseInfo.manualCount) { lottos.add(getManualNumber()) }
        return lottos
    }

    private fun getManualNumber(): Lotto {
        while (true) {
            val lotto = readln().split(",").map { it.trim().toIntOrNull() ?: -1 }
            runCatching { Lotto(lotto.map { LottoNumber.from(it) }.toSet()) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    override fun getWinningNumbers(): Lotto {
        while (true) {
            println(INPUT_MESSAGE_FOR_WINNING_NUMBERS)
            val numbers = readln()
            runCatching { Lotto(numbers.split(",").map { LottoNumber.from(it.trim().toInt()) }.toSet()) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    override fun getBonusNumber(): LottoNumber {
        while (true) {
            println(INPUT_MESSAGE_FOR_BONUS_NUMBER)
            val bonusNumber = readln()
            runCatching { LottoNumber.from(bonusNumber.toInt()) }
                .onSuccess { return it }
                .onFailure { println(it.message) }
        }
    }

    companion object {
        private const val INPUT_MESSAGE_FOR_PURCHASE_MONEY = "구입 금액을 입력해 주세요."
        private const val INPUT_MESSAGE_MANUAL_LOTTO_NUMBERS = "수동으로 구매할 번호를 입력해 주세요."
        private const val INPUT_MESSAGE_MANUAL_LOTTO_COUNT = "수동으로 구매할 로또 수를 입력해 주세요."
        private const val INPUT_MESSAGE_FOR_WINNING_NUMBERS = "지난 주 당첨 번호를 입력해 주세요."
        private const val INPUT_MESSAGE_FOR_BONUS_NUMBER = "보너스 볼을 입력해 주세요."
    }
}
