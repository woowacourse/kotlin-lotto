package lotto.entity

data class LottoNumber private constructor(val value: Int) {
    companion object {
        const val ERROR_MESSAGE_RANGE_1_TO_45 = "당첨 번호는 1에서 45 사이의 숫자여야 합니다. 입력된 당첨 번호는 %d 입니다."
        val lottoNumberCache = Lotto.LOTTO_RANGE.map { LottoNumber(it) }

        fun from(value: Int): LottoNumber {
            require(value in Lotto.LOTTO_RANGE) { ERROR_MESSAGE_RANGE_1_TO_45.format(value) }
            return lottoNumberCache[value - 1]
        }
    }
}
