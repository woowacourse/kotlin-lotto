package domain.model.machine

import domain.model.lotto.Lotto
import domain.model.lotto.Lotto.Companion.LOTTO_SIZE
import domain.model.number.LottoNumber
import domain.model.number.LottoNumber.Companion.LOTTO_MAX
import domain.model.number.LottoNumber.Companion.LOTTO_MIN
import kotlin.collections.shuffled

object AutoLottoMachine : LottoMachine {
    override fun generate(): Lotto {
        val range = (LOTTO_MIN..LOTTO_MAX)
        val randomNumbers = (range).shuffled().take(LOTTO_SIZE)
        val lottoNumbers = randomNumbers.map { LottoNumber(it) }.toSet()
        return Lotto(lottoNumbers)
    }
}
