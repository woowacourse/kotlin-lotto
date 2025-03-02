package lotto.domain.model

class LottoBundle(val lottos: List<Lotto>) {
    init {
        require(lottos.size > MIN_LOTTO_COUNT_SIZE) { INVALID_LOTTO_COUNT_MESSAGE }
    }

    companion object {
        private const val INVALID_LOTTO_COUNT_MESSAGE = "로또는 한 장 이상 구매해야 합니다."
        private const val MIN_LOTTO_COUNT_SIZE = 0

        fun combine(
            bundle1: LottoBundle?,
            bundle2: LottoBundle?,
        ): LottoBundle {
            val combinedLottos = (bundle1?.lottos ?: emptyList()) + (bundle2?.lottos ?: emptyList())
            return LottoBundle(combinedLottos)
        }
    }
}
