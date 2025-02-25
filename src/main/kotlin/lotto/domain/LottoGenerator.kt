package lotto.domain

import lotto.view.View

class LottoGenerator(
    private val totalQuantity: Int,
    private val manualQuantity: Int,
) {
    init {
        require(manualQuantity >= 0) { ERROR_MESSAGE_NOT_ENOUGH_MANUAL_LOTTOS }
        require(totalQuantity >= manualQuantity) { ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS }
    }

    fun makeManualLottos(): Lottos {
        View.requestManualNumbers()
        return Lottos(List(manualQuantity) { Lotto(View.readManualNumbers().map(::LottoNumber).toSet()) })
    }

    fun makeAutomaticLottos(): Lottos {
        return Lottos(List(totalQuantity - manualQuantity) { Lotto(makeLottoNumbers()) })
    }

    private fun makeLottoNumbers(): Set<LottoNumber> = LottoNumber.RANGE.shuffled().subList(0, Lotto.SIZE).map(::LottoNumber).toSet()

    companion object {
        private const val ERROR_MESSAGE_NOT_ENOUGH_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 0보다 작을 수 없습니다."
        private const val ERROR_MESSAGE_TOO_MANY_MANUAL_LOTTOS = "수동으로 구매할 로또의 개수는 로또의 총 개수를 초과할 수 없습니다."
    }
}
