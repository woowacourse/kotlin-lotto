package lotto.domain

class Lottos(
    val lottos: Set<Lotto>,
) {
    companion object {
        fun buy(
            price: Int,
            vararg lottos: Lotto,
        ): Lottos {
            require(price >= Lotto.LOTTO_PRICE) { ERROR_MESSAGE_NOT_ENOUGH_MONEY }
            val count = price / Lotto.LOTTO_PRICE
            require(count == lottos.size) { ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT }
            return Lottos(lottos.toSet())
        }

        private const val ERROR_MESSAGE_NOT_ENOUGH_MONEY = "최소 구입 금액은 ${Lotto.LOTTO_PRICE}원입니다."
        private const val ERROR_MESSAGE_NOT_MATCHED_MONEY_AND_LOTTOS_COUNT = "구입 금액과 로또의 개수가 일치하지 않습니다."
    }
}
