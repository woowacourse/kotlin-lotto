package lotto.model

import lotto.model.Lotto.Companion.LOTTO_NUMBER_MAX_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_MIN_RANGE
import lotto.model.Lotto.Companion.LOTTO_NUMBER_SIZE

class AutoLottoGenerator : LottoGenerator {
    override fun getLottoNumbers(): Lotto {
        val shuffledLottoNumbers = (LOTTO_NUMBER_MIN_RANGE..LOTTO_NUMBER_MAX_RANGE).shuffled()
        val selectedLottoNumbers = shuffledLottoNumbers.take(LOTTO_NUMBER_SIZE).sorted()
        val selectedLotto = selectedLottoNumbers.map { LottoNumber(it) }.toSet()

        return Lotto(selectedLotto)
    }
}
