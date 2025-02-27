package lotto.model

import lotto.contants.LottoRuleConstants

class LottoStoreCashier(
    private val money: Int,
) {
    init {
        require(money >= LottoRuleConstants.LOTTO_AMOUNT.value) { "로또는 천원이상 넣어야지 구매 가능합니다." }
    }

    // 거스름 돈을 계산
    fun calculateChange(): Int {
        if (money % LottoRuleConstants.LOTTO_AMOUNT.value != 0) {
            return money - LottoRuleConstants.LOTTO_AMOUNT.value * calculateCount()
        }
        return 0
    }

    // 로또 몇 장 발행가능 한지 계산
    fun calculateCount(): Int {
        return money / LottoRuleConstants.LOTTO_AMOUNT.value
    }
}