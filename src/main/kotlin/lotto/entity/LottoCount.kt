package lotto.entity

class LottoCount(val value: Int) {
    operator fun minus(other: LottoCount): LottoCount = LottoCount(value - other.value)
}
