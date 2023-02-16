package lotto.model

import lotto.view.ERROR_OUT_OF_RANGE

class LottoNumber(val number: Int) {

    init {
        require(number in 1..45) { ERROR_OUT_OF_RANGE }
    }
}
