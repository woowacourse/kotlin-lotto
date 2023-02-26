package lotto.domain.model

import lotto.controller.Controller

data class Lotto(val numbers: Set<LottoNumber>) {
    init {
        require(checkLottoAvailable(numbers)) { LOTTO_SIZE_ERROR }
    }

    fun countMatchingNumbers(winningLotto: Lotto): Int {
        return numbers.count { lottoNumber -> winningLotto.numbers.contains(lottoNumber) }
    }

    fun checkMatchingBonusNumber(bonusNumber: LottoNumber): Boolean {
        return numbers.any { lottoNumber -> lottoNumber == bonusNumber }
    }

    companion object {
        fun checkLottoAvailable(numbers: Set<LottoNumber>): Boolean {
            if (numbers.size == LOTTO_SIZE) return true
            println(Controller.ERROR_PREFIX + LOTTO_SIZE_ERROR)
            return false
        }

        const val LOTTO_SIZE = 6
        const val LOTTO_SIZE_ERROR = "로또 번호는 중복 되지 않는 여섯 개의 숫자입니다."
    }
}
