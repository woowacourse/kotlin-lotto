package lotto.model

interface LottoMachine {
    fun generate(lottoSize: Int): List<Lotto>
}
