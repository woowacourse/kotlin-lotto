package lotto.domain

class Lottos(
    val value: List<Lotto>,
) {
    companion object {
        fun buy(
            price: Int,
            lottos: List<Lotto>,
        ): Lottos {
            val lottoTicketCount: Int = price.toLottoTicketCount()
            require(lottoTicketCount == lottos.size) { ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT }
            return Lottos(lottos)
        }

        private fun Int.toLottoTicketCount(): Int {
            require(this >= Lotto.PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
            return this / Lotto.PRICE
        }

        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "최소 구입 금액은 ${Lotto.PRICE}원입니다."
        private const val ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT = "구입 금액과 로또의 개수가 일치하지 않습니다."
    }
}
