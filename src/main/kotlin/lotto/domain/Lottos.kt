package lotto.domain

class Lottos(
    val value: List<Lotto>,
) {
    init {
        require(value.isNotEmpty()) { ERROR_MESSAGE_NOT_ENOUGH_PURCHASE }
    }

    companion object {
        fun getResult(
            winningLotto: WinningLotto,
            lottos: Lottos,
        ): LottoResults {
            return LottoResults(lottos.value.map { lotto -> LottoResult.from(winningLotto, lotto) })
        }

        private const val ERROR_MESSAGE_NOT_ENOUGH_PURCHASE = "적어도 한 개의 로또를 구입해야 합니다."
    }
}
