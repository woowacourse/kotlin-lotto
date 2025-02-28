package lotto.domain.service

import lotto.domain.model.Lotto
import lotto.domain.model.Lotto.Companion.LOTTO_NUMBER_SIZE
import lotto.domain.model.LottoNumber.Companion.LOTTO_MAX_NUMBER
import lotto.domain.model.LottoNumber.Companion.LOTTO_MIN_NUMBER
import lotto.domain.model.LottoResult

class LottoMachineImpl : LottoMachine {
    override fun generateRandomLottoNumbers(): Lotto {
        val lottoNumbers = getRandomLottoNumbers().sorted()
        val lottoResult = Lotto.from(lottoNumbers)
        require(lottoResult is LottoResult.Success)
        return lottoResult.lotto
    }

    private fun getRandomLottoNumbers(): List<Int> {
        val randomLottoNumbers = lottoNumbers.shuffled()
        return randomLottoNumbers.take(LOTTO_NUMBER_SIZE)
    }

    companion object {
        private val lottoNumbers = LOTTO_MIN_NUMBER..LOTTO_MAX_NUMBER
    }
}
