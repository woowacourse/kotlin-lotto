package lotto.model

import lotto.exception.ErrorCode.DUPLICATE_NUMBER
import lotto.exception.ExceptionsHandler.handleValidation

class Lotto(val lottoNumbers: Set<LottoNumber>) {
    init {
        handleValidation(DUPLICATE_NUMBER) { lottoNumbers.size == LOTTO_SIZE }
    }

    override fun toString(): String = lottoNumbers.toString()

    fun getCountOfMatch(lotto: Lotto): Int = lottoNumbers.intersect(lotto.lottoNumbers.toSet()).size

    companion object {
        const val LOTTO_SIZE = 6
    }
}
