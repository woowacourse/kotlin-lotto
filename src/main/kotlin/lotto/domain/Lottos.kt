package lotto.domain

class Lottos(
    val value: List<Lotto>,
) {
    companion object {
        fun buy(
            count: Int,
            lottos: List<Lotto>,
        ): Lottos {
            require(count > 0) { ERROR_MESSAGE_NOT_ENOUGH_PURCHASE }
            require(count == lottos.size) { ERROR_MESSAGE_PAYMENT_AND_LOTTO_COUNT_MISMATCH }
            return Lottos(lottos)
        }

        fun getResult(winningLotto: WinningLotto, lottos: Lottos): LottoResults {
            return LottoResults(lottos.value.map { lotto -> LottoResult.from(winningLotto, lotto) })
        }

        private const val ERROR_MESSAGE_NOT_ENOUGH_PURCHASE = "적어도 한 개의 로또를 구입해야 합니다."
        private const val ERROR_MESSAGE_PAYMENT_AND_LOTTO_COUNT_MISMATCH = "구입 금액과 로또의 개수가 일치하지 않습니다."
    }
}
