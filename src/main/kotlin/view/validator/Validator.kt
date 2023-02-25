package view.validator

import domain.Lotto
import domain.LottoNumber
import domain.LottoPurchaseInfo
import domain.PurchaseLottoMoney
import domain.WinningLotto

object Validator {
    fun validateConvertInt(input: String): Int? {
        val number = input.toIntOrNull()
        if (number == null)
            println(ERROR_CONVERT_INT)
        return number
    }

    fun validateConvertToIntList(input: String, delimiter: String): List<Int>? {
        val numbers = input.split(delimiter).map { it.trim().toIntOrNull() }
        if (numbers.contains(null)) {
            println(ERROR_CONVERT_INT_LIST.format(delimiter))
            return null
        }
        return numbers.map { it!! }
    }

    fun validateMakePurchaseLottoMoney(money: Int): PurchaseLottoMoney? {
        if (money < PurchaseLottoMoney.ONE_LOTTO_MONEY) {
            println(ERROR_MAKE_LOTTO_PURCHASE_MONEY)
            return null
        }
        return PurchaseLottoMoney(money)
    }

    fun validateMakeLottoPurchaseInfo(purchaseLottoMoney: PurchaseLottoMoney, manualCount: Int): LottoPurchaseInfo? {
        if (purchaseLottoMoney.purchaseCount < manualCount || manualCount < 0) {
            println(ERROR_MAKE_LOTTO_PURCHASE_INFO)
            return null
        }
        return LottoPurchaseInfo(purchaseLottoMoney, manualCount)
    }

    fun validateMakeLotto(numbers: List<Int>): Lotto? {
        if (numbers.any { it !in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE }) {
            println(ERROR_MAKE_LOTTO_TO_RANGE)
            return null
        }
        if (numbers.distinct().size != Lotto.LOTTO_SIZE || numbers.size != Lotto.LOTTO_SIZE) {
            println(ERROR_MAKE_LOTTO_TO_SIZE)
            return null
        }
        return Lotto(numbers.map { LottoNumber.from(it) }.toSet())
    }

    fun validateMakeLottoNumber(number: Int): LottoNumber? {
        if (number in LottoNumber.MINIMUM_LOTTO_RANGE..LottoNumber.MAXIMUM_LOTTO_RANGE) return LottoNumber.from(number)
        println(ERROR_MAKE_LOTTO_NUMBER)
        return null
    }

    fun validateMakeWinningLotto(winningNumbers: Lotto, bonusNumber: LottoNumber): WinningLotto? {
        if (winningNumbers.contains(bonusNumber)) {
            println(ERROR_MAKE_WINNING_LOTTO_TO_DISTINCT)
            return null
        }
        return WinningLotto(winningNumbers, bonusNumber)
    }

    private const val ERROR_CONVERT_INT = "[ERROR] 숫자로 다시 입력해주세요."
    private const val ERROR_CONVERT_INT_LIST = "[ERROR] %s로 숫자를 구분해서 다시 입력해주세요."
    private const val ERROR_MAKE_LOTTO_PURCHASE_MONEY =
        "[ERROR] 로또를 구입하기 위해서는 최소한 ${PurchaseLottoMoney.ONE_LOTTO_MONEY}원 이상의 금액이여야 합니다."
    private const val ERROR_MAKE_LOTTO_PURCHASE_INFO =
        "[ERROR] 수동 발급 개수는 발급 가능한 총 개수를 넘을 수 없는 음이 아닌 수여야 합니다."
    private const val ERROR_MAKE_LOTTO_TO_RANGE =
        "[ERROR] 각 숫자는 ${LottoNumber.MINIMUM_LOTTO_RANGE}에서 ${LottoNumber.MAXIMUM_LOTTO_RANGE} 사이로 구성되어야 합니다."
    private const val ERROR_MAKE_LOTTO_TO_SIZE = "[ERROR] 로또는 서로 다른 ${Lotto.LOTTO_SIZE}개의 숫자로 구성되어야 합니다."
    private const val ERROR_MAKE_LOTTO_NUMBER =
        "[ERROR] 로또의 숫자는 ${LottoNumber.MINIMUM_LOTTO_RANGE}에서 ${LottoNumber.MAXIMUM_LOTTO_RANGE} 사이여야 합니다."
    private const val ERROR_MAKE_WINNING_LOTTO_TO_DISTINCT = "[ERROR] 당첨번호와 보너스 번호 사이에 중복이 있으면 안됩니다."
}
