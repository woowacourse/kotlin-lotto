package domain

interface LottoMachine {
    fun create(count: Int): List<Lotto>
}
