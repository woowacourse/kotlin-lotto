package lotto.model

import lotto.contants.LottoRuleConstants

class LottoStoreCashier(
    private val customerMoney: Int,
) {
    private val possibleToLottoTicketCount = calculatePossibleToBuyLottoTicketCount()

    init {
        require(customerMoney >= LottoRuleConstants.LOTTO_AMOUNT.value) { INSUFFICIENT_MONEY_FOR_LOTTO_PURCHASE }
    }

    fun calculatePossibleToBuyLottoTicketCount(): Int = customerMoney / LottoRuleConstants.LOTTO_AMOUNT.value

    fun calculateChange(customerWantBuyLottoTicketCount: Int): Int =
        customerMoney - (LottoRuleConstants.LOTTO_AMOUNT.value * possibleToLottoTicketCount)

    companion object {
        private const val INSUFFICIENT_MONEY_FOR_LOTTO_PURCHASE = "로또는 천원이상 넣어야지 구매 가능합니다."
    }
}
