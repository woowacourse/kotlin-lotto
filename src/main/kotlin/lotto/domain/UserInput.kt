package lotto.domain

import lotto.global.Message

data class UserInput(
    val buyAmount: Int,
    val manualLottoCount: Int,
    private val rawManualLotto: List<List<Int>>,
) {
    val manualLotto: List<Lotto> = rawManualLotto.map { Lotto(it.map { LottoNumber.of(it) }) }
    val totalLottoCount: Int = buyAmount / LOTTO_PRICE
    val automaticLottoCount: Int = totalLottoCount - manualLottoCount

    init {
        require(buyAmount >= LOTTO_PRICE) { Message.ERR_LESS_THAN_MINIMUM_PRICE.msg }
        require(manualLottoCount <= totalLottoCount) { Message.ERR_TOO_MANY_MANUAL_LOTTO.msg }
        require(manualLotto.size == manualLottoCount) { Message.ERR_MANUAL_NOT_SUFFICIENT.msg }
    }
}
