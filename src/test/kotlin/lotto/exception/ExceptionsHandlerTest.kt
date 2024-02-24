package lotto.exception

import lotto.exception.ErrorCode.BONUS_NUMBER_DUPLICATE
import lotto.exception.ErrorCode.DUPLICATE_NUMBER
import lotto.exception.ErrorCode.INVALID_NUMBER_EXCEPTION
import lotto.exception.ErrorCode.INVALID_PURCHASE_AMOUNT
import lotto.exception.ErrorCode.LOTTO_NUMBER_OUT_OF_RANGE
import lotto.exception.ErrorCode.PURCHASE_AMOUNT_NOT_NATURAL_NUMBER
import lotto.exception.ExceptionsHandler.handleValidation
import lotto.model.Lotto
import lotto.model.LottoMachine
import lotto.model.LottoNumber
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class ExceptionsHandlerTest {
    @ParameterizedTest
    @CsvSource("1,2,3,3,4,5", "1,2,3,4,4,5", "1,2,3,4,45,45")
    fun `로또의 숫자들이 중복되면 DuplicateNumbersException 예외처리가 발생한다`(strNumber: String) {
        assertThrows<Exceptions.DuplicateNumbersException> {
            handleValidation(DUPLICATE_NUMBER) { strNumber.toSet().size == 6 }
        }
    }

    @ParameterizedTest
    @CsvSource("7", "5", "4", "3")
    fun `보너스 숫자가 당첨번호와 중복되면 BonusNumberDuplicateException 예외처리가 발생한다`(strNumber: String) {
        val winningLotto =
            Lotto(
                setOf(
                    LottoNumber(1),
                    LottoNumber(2),
                    LottoNumber(3),
                    LottoNumber(4),
                    LottoNumber(5),
                    LottoNumber(7),
                ),
            )

        val bonusNumber = LottoNumber.from(strNumber)

        assertThrows<Exceptions.BonusNumberDuplicateException> {
            handleValidation(BONUS_NUMBER_DUPLICATE) { bonusNumber !in winningLotto.lottoNumbers }
        }
    }

    @ParameterizedTest
    @CsvSource("한글", "asd")
    fun `구입 금액이 숫자가 아니라면 PurchaseAmountNotNaturalNumberException 예외처리가 발생한다`(price: String) {
        assertThrows<Exceptions.PurchaseAmountNotNaturalNumberException> {
            handleValidation(PURCHASE_AMOUNT_NOT_NATURAL_NUMBER) { price.toIntOrNull() != null }
        }
    }

    @ParameterizedTest
    @CsvSource("999", "1", "0")
    fun `구입 금액이 1000 이상이 아니라면 InvalidPurchaseAmountException 예외처리가 발생한다`(price: String) {
        assertThrows<Exceptions.InvalidPurchaseAmountException> {
            handleValidation(INVALID_PURCHASE_AMOUNT) {
                price.toIntOrNull()?.let { it >= LottoMachine.MIN_PRICE } == true
            }
        }
    }

    @ParameterizedTest
    @CsvSource("46", "47", "0")
    fun `로또의 숫자가 1에서 45가 아니라면 LottoNumberOutOfRangeException 예외처리가 발생한다`(strNumber: String) {
        assertThrows<Exceptions.LottoNumberOutOfRangeException> {
            handleValidation(LOTTO_NUMBER_OUT_OF_RANGE) {
                strNumber.toIntOrNull() in LottoNumber.LOTTO_NUMBER_RANGE
            }
        }
    }

    @ParameterizedTest
    @CsvSource("한글", "asd")
    fun `로또가 숫자가 아니라면 InvalidNumberException 예외처리가 발생한다`(strNumber: String) {
        assertThrows<Exceptions.InvalidNumberException> {
            handleValidation(INVALID_NUMBER_EXCEPTION) {
                strNumber.toIntOrNull() != null
            }
        }
    }
}
