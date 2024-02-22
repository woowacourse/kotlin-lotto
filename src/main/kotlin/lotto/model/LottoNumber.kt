package lotto.model

import lotto.constant.LottoConstant

data class LottoNumber(val number: Int) {
    init {
        require(number in LottoConstant.NUMBER_RANGE) { LOTTO_RANGE_ERROR_MESSAGE }
    }

    companion object {
        private const val LOTTO_RANGE_ERROR_MESSAGE = "로또의 숫자들은 1부터 45까지의 숫자로 구성되어야 합니다."
    }
}
