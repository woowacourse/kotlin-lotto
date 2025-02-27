package lotto.model

import lotto.contants.LottoRuleConstants

// 돈 계산을 해주는 클래스
class LottoStoreCashier(
    private val customerMoney: Int,
) {
    private val possibleToLottoTicketCount = calculatePossibleToBuyLottoTicketCount()

    // 로또의 가격은 천원이다. 로또를 구매할려면 천원이상 넣어야한다.
    init {
        require(customerMoney >= LottoRuleConstants.LOTTO_AMOUNT.value) { "로또는 천원이상 넣어야지 구매 가능합니다." }
    }

    // 고객이 준 돈에서 몇장을 살 수 있는 지 계산한다.
    private fun calculatePossibleToBuyLottoTicketCount(): Int = customerMoney / LottoRuleConstants.LOTTO_AMOUNT.value

    // 고객이 몇장을 사고 싶다고 이야기를 하면 가능한지 계산한다.
    fun isPossibleToBuy(customerWantBuyLottoTicketCount: Int): Boolean = possibleToLottoTicketCount >= customerWantBuyLottoTicketCount

    // 고객에게 전달할 잔돈을 계산한다.
    fun calculateChange(customerWantBuyLottoTicketCount: Int): Int = customerMoney - (LottoRuleConstants.LOTTO_AMOUNT.value * possibleToLottoTicketCount)
}