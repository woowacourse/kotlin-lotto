package lotto.model

import lotto.util.Constant

data class LottoNumber(val lottoNumber: Int) {
    init {
        require(lottoNumber in Constant.LOTTO_NUM_RANGE)
    }
}
