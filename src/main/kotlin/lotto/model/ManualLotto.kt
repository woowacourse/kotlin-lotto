package lotto.model

import lotto.view.ERROR_OUT_OF_LOTTO_NUMBER

class ManualLotto(val numberOfLotto: Int) {

    fun validateNumberOfLotto(totalNumber: Int): ManualLotto {
        require(totalNumber >= numberOfLotto) { ERROR_OUT_OF_LOTTO_NUMBER }
        return this
    }
}
