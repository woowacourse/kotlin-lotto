package lotto.domain.model

import lotto.constants.ErrorMessages
import lotto.domain.value.LottoCount

class LottoOrder(
    private val totalLottoCount: LottoCount,
    private val manualLottoCount: LottoCount,
    private val manualLottoNumbers: List<List<Int>>? = null,
) {
    init {
        require(totalLottoCount.count >= manualLottoCount.count) { ErrorMessages.INVALID_MANUAL_LOTTO_COUNT }
    }
}
