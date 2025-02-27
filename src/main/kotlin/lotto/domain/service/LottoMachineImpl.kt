package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.domain.model.LottoNumber
import lotto.domain.model.LottoNumber.Companion.LOTTO_MAX_NUMBER
import lotto.domain.model.LottoNumber.Companion.LOTTO_MIN_NUMBER

class LottoMachineImpl : LottoMachine {
    override fun generateRandomLottoNumbers(): Lotto {
        val lotto = getRandomLottoNumbers().sorted()
        return Lotto(lotto.toSet())
    }

    private fun getRandomLottoNumbers(): List<LottoNumber> {
        val randomLottoNumbers = lottoNumbers.shuffled()
        return randomLottoNumbers.take(LOTTO_NUMBER_SIZE)
    }

    override fun generateManualLottoNumbers(numbers: List<Int>): Lotto {
        val lotto = getManualLottoNumbers(numbers).sorted()
        return Lotto(lotto.toSet())
    }

    private fun getManualLottoNumbers(numbers: List<Int>): List<LottoNumber> {
        return numbers.map { LottoNumber(it) }
    }

    companion object {
        private val lottoNumbers = (LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER).map { LottoNumber(Integer.valueOf(it)) }
    }
}
