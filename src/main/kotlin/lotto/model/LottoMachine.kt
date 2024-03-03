package lotto.model

fun interface LottoMachine {
    fun generate(lottoSize: Int): List<Lotto>
}
