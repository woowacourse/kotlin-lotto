package model

class Bonus(val number: LottoNumber, private val lotto: Lotto) {
    constructor(input: String, lotto: Lotto) :
            this(LottoNumber(input), lotto)

    init {
        require(validateUnique(number, lotto)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    private fun validateUnique(
        number: LottoNumber,
        lotto: Lotto,
    ) = !lotto.contains(number)

    companion object {
        const val EXCEPTION_DUPLICATED_NUMBER = "보너스 번호는 로또 번호에 포함될 수 없습니다"
    }
}
