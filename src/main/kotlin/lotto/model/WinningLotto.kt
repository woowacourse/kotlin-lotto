package lotto.model

class WinningLotto(val lotto: Lotto, val bonusNumber: LottoNumber) {
    init {
        require(!lotto.contains(bonusNumber)) {
            INVALID_DUPLICATE_BONUS_NUMBER
        }
    }

    fun calculatePrizeCount(lottoStore: LottoStore) =
        lottoStore.lottos
            .map { it.compare(lotto, bonusNumber) }
            .groupBy { it }
            .mapValues { it.value.size }

    companion object {
        private const val INVALID_DUPLICATE_BONUS_NUMBER = "당첨 번호와 중복되지 않는 보너스 번호를 입력해 주세요."
    }
}
