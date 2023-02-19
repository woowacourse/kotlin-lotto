package domain

import model.Lotto
import model.LottoNumber

class LottoMachine(private val lottoNumbersGenerator: NumbersGenerator) {

    fun generateLotto(): Lotto {
        val numbers = lottoNumbersGenerator.generate()
        val lottoNumbers = numbers.map { number -> LottoNumber.from(number) }
        return Lotto(lottoNumbers)
    }
}
