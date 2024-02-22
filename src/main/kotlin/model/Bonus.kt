package model

class Bonus(val number: LottoNumber, winningLotto: Lotto) {
    constructor(input: String, winningLotto: Lotto) :
        this(LottoNumber(input), winningLotto)

    init {
        require(validateUnique(number, winningLotto)) { EXCEPTION_DUPLICATED_NUMBER }
    }

    private fun validateUnique(
        number: LottoNumber,
        winningLotto: Lotto,
    ) = !winningLotto.contains(number)

    companion object {
        const val EXCEPTION_DUPLICATED_NUMBER = "보너스 번호는 로또 번호에 포함될 수 없습니다"
    }
}
