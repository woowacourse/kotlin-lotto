package lotto.entity

class LottoCount(val value: Int) {

    operator fun minus(lottoCount: LottoCount): LottoCount = LottoCount(value - lottoCount.value)
}
