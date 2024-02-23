package lotto.model

data class LottoNumber(val number: Int) {
    init {
        require(number in NUMBER_RANGE) { "로또 번호의 범위는 1~45 사이의 자연수입니다." }
    }

    constructor(inputNumber: String) : this(inputNumber.toInt())

    override fun toString(): String = number.toString()

    companion object {
        val NUMBER_RANGE = (1..45)
    }
}
