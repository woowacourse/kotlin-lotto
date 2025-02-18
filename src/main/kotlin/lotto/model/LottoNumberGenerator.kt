package lotto.model

class LottoNumberGenerator {
    fun getLottoNumbers(): List<Int> = List(6) { getLottoNumber() }

    private fun getLottoNumber(): Int = (1..45).random()
}
