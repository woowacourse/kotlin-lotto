package lotto.domain.model

class LottoBundle(val lottos: List<Lotto>) {
    init {
        require(lottos.size > MIN_LOTTO_COUNT_SIZE) { INVALID_LOTTO_COUNT_MESSAGE }
    }

    private companion object {
        const val INVALID_LOTTO_COUNT_MESSAGE = "로또는 한 장 이상 구매해야 합니다."
        const val MIN_LOTTO_COUNT_SIZE = 0
    }
}
