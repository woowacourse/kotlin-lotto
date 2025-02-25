package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.domain.value.LottoCount

class LottoOrder(
    private val totalLottoCount: LottoCount,
    val manualLottoCount: LottoCount,
    val manualLottoNumbers: List<List<Int>>? = null,
) {
    init {
        require(totalLottoCount.count >= manualLottoCount.count) { ErrorMessages.INVALID_MANUAL_LOTTO_COUNT }
    }

    fun getAutomaticLottoCount(): LottoCount = LottoCount(totalLottoCount.count - manualLottoCount.count)
}
