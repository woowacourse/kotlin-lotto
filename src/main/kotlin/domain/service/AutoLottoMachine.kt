package domain.service

import domain.model.lotto.Lotto
import domain.model.lotto.Lotto.Companion.LOTTO_SIZE
import domain.model.number.LottoNumber
import domain.model.number.LottoNumber.Companion.LOTTO_MAX
import domain.model.number.LottoNumber.Companion.LOTTO_MIN
import kotlin.collections.shuffled

object AutoLottoMachine : LottoMachine {
    override fun generate(): Lotto {
        return Lotto((LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) })
    }
}
