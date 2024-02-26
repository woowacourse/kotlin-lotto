package lotto.model

import lotto.util.Constant

data class LottoNumber(val lottoNumber: Int) {
    companion object {
        private val cache = (Constant.LOTTO_NUM_RANGE).associateWith { LottoNumber(it) }

        fun of(lottoNumber: Int): LottoNumber {
            require(lottoNumber in Constant.LOTTO_NUM_RANGE) {
                "로또 숫자는 ${Constant.LOTTO_NUM_RANGE.first}~${Constant.LOTTO_NUM_RANGE.last} 범위 내에 존재해야 합니다."
            }
            return cache[lottoNumber] ?: throw IllegalArgumentException("Invalid Lotto Number")
        }
    }
}
