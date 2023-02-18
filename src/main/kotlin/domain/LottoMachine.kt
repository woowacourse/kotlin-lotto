package domain

interface LottoMachine {
    fun create(start: Int = LottoNumber.valueOfMinRange(), end: Int = LottoNumber.valueOfMaxRange()): Lotto
}
