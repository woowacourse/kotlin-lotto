package lotto.model

import lotto.util.Constant

data class LottoNumber(val lottoNumber: Int) {
    init {
        require(lottoNumber in Constant.LOTTO_NUM_RANGE) {
            "로또 숫자는 1~45 범위 내에 존재해야 합니다."
        }
    }
}
