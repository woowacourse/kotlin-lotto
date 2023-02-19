package domain.lottogenerator

import domain.model.lotto.Lotto
import domain.model.lotto.LottoNumber

open class ManualLottoGenerator {

    fun generate(InputNumbers: List<Int>): Lotto {
        val lottoNumbers = InputNumbers.map { number ->
            LottoNumber.from(number)
        }.toSet()

        return Lotto(lottoNumbers)
    }
}
