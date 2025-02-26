package domain.service

import domain.model.LottoNumber
import domain.model.LottoNumber.Companion.LOTTO_MAX
import domain.model.LottoNumber.Companion.LOTTO_MIN
import domain.model.lotto.Lotto
import domain.model.lotto.Lotto.Companion.LOTTO_SIZE
import kotlin.collections.shuffled

object AutoLottoMachine : LottoMachine {
    override fun generate(): Lotto {
        return Lotto((LOTTO_MIN..LOTTO_MAX).shuffled().take(LOTTO_SIZE).map { LottoNumber(it) })
    }
}
