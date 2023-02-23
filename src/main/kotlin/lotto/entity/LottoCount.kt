package lotto.entity

class LottoCount(val value: Int) {

    fun calculateAutoLottoCount(manualLottoCount: LottoCount): LottoCount = LottoCount(value - manualLottoCount.value)
}
